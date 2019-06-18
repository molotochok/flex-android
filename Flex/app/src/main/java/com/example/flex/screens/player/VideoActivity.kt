package com.example.flex.screens.player

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.example.flex.R
import kotlinx.android.synthetic.main.activity_video.*


class VideoActivity : FragmentActivity() {

    lateinit var videoService: VideoService
    val state = PlayerState()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        Log.i("kek", intent.getStringExtra("streamingUrl")!!)
        val uri = Uri.parse(intent.getStringExtra("streamingUrl"))

        videoService = VideoService(this, exoplayerview_activity_video, state, uri)
    }


    override fun onStart() {
        super.onStart()
        videoService.start()
    }

    override fun onStop() {
        super.onStop()
        videoService.stop()
    }


    override fun onDestroy() {
        super.onDestroy()
        videoService.release()
    }
}
