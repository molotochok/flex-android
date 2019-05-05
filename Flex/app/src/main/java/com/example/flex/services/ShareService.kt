package com.example.flex.services

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class ShareService(private val activity: AppCompatActivity) {

    fun share(data : String){
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_TEXT, data)
        activity.startActivity(Intent.createChooser(sharingIntent, "Share via"))
    }
}