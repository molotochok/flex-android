package com.example.flex

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScanActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    private var scannerView: ZXingScannerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Programmatically initialize the scanner view
        scannerView = ZXingScannerView(this)
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
        // Do something with the result here
        Log.v("tag", rawResult!!.text) // Prints scan results
        Log.v("tag", rawResult!!.barcodeFormat.toString()) // Prints the scan format (qrcode, pdf417 etc.)


        onBackPressed()

        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
    }
}
