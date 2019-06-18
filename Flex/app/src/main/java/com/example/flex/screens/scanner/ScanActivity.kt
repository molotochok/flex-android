package com.example.flex.screens.scanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.zxing.BarcodeFormat
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView
import com.moandjiezana.toml.Toml
import java.lang.Exception


class ScanActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    private var scannerView: ZXingScannerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Programmatically initialize the scanner view
        scannerView = ZXingScannerView(this)
        scannerView!!.setFormats(mutableListOf(BarcodeFormat.QR_CODE))
        // Set the scanner view as the content view
        setContentView(scannerView)
    }

    override fun onResume(){
        super.onResume()

        // Register ourselves as a handler for scan results.
        scannerView!!.setResultHandler(this)
        // Start camera on resume
        scannerView!!.startCamera()
    }

    override fun onPause(){
        super.onPause()

        // Stop camera on pause
        scannerView!!.stopCamera()
    }

    override fun handleResult(rawResult: Result?) {
        val builder = AlertDialog.Builder(this)

        var toml:Toml
        try{
            toml = Toml().read(rawResult!!.text)
        }catch(ex:Exception){
            builder.setMessage("Wrong qr-code")
            builder.setPositiveButton("Ok"){dialog, which ->
                scannerView!!.resumeCameraPreview(this)
            }
            builder.create().show()
            return
        }

        val server = toml.to(Server::class.java)

        builder.setMessage(server.flex.toString())

        builder.setPositiveButton("Ok"){dialog, which ->
            scannerView!!.resumeCameraPreview(this)
        }

        builder.create().show()

        //onBackPressed()
    }
}
