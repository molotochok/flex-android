package com.example.flex

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.flex.services.PermissionService
import kotlin.reflect.KClass

class FirstActivity : AppCompatActivity() {
    private val permissionService = PermissionService(this, Manifest.permission.CAMERA)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        for(p in permissions){
            when(p){
                Manifest.permission.CAMERA ->{
                    if(permissionService.checkPermission(p))
                        openActivity(ScanActivity::class)
                }
            }
        }
    }

    // public methods
    fun openScanReader(view: View){
        if (permissionService.checkAndRequestPermissions()){
            openActivity(ScanActivity::class)
        }
    }

    // TODO: REMOVE
    fun openVideoPlayer(videe: View) {
        openActivity(VideoActivity::class)
    }


    // private methods
    private fun<T> openActivity(activityClass: KClass<T>) where T: Activity {
        val intent = Intent(this, activityClass.java)
        startActivity(intent)
    }
}
