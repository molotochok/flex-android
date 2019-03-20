package com.example.flex

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import com.example.flex.Services.PlayerState
import com.example.flex.Services.VideoService
import kotlinx.android.synthetic.main.activity_video.*


class VideoActivity : FragmentActivity() {

    lateinit var videoService: VideoService
    val state = PlayerState()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        videoService = VideoService(this, exoplayerview_activity_video, state)
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
