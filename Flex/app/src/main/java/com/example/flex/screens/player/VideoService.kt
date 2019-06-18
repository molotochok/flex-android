package com.example.flex.screens.player

import android.content.Context
import android.net.Uri
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class VideoService(val context: Context,
                   val playerView: PlayerView,
                   val playerState: PlayerState,
                   val uri: Uri) : AnkoLogger {


    private val player: ExoPlayer
    // constructor
    init {
        // Create the player instance.
        player = ExoPlayerFactory.newSimpleInstance(context, DefaultTrackSelector())
            .also {
                playerView.player = it // Bind to the view.
                info { "SimpleExoPlayer created" }
            }
    }

    fun start() {
        // Load media.
        player.prepare(buildMediaSource(uri))
        // Restore state (after onResume()/onStart())
        with(playerState) {
            // Start playback when media has buffered enough
            // (whenReady is true by default).
            player.playWhenReady = whenReady
            player.seekTo(window, position)
        }
        info { "SimpleExoPlayer is started" }
    }

    fun stop() {
        with(player) {
            // Save state
            with(playerState) {
                position = currentPosition
                window = currentWindowIndex
                whenReady = playWhenReady
            }
            // Stop the player.
            stop()
        }
        info { "SimpleExoPlayer is stopped" }
    }

    fun release() {
        with(player) {
            // Save state
            with(playerState) {
                position = currentPosition
                window = currentWindowIndex
                whenReady = playWhenReady
            }
            // Stop the player.
            release()
        }
        info { "SimpleExoPlayer is released" }
    }

    // Private methods
    private fun buildMediaSource(uri: Uri): MediaSource {
        return ExtractorMediaSource.Factory(DefaultDataSourceFactory(context, "flex"))
            .createMediaSource(uri)
    }
}