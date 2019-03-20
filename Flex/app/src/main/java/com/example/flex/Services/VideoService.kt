package com.example.flex.Services

import android.content.Context
import android.net.Uri
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.lang.Exception

enum class SourceType {
    LOCAL_AUDIO, LOCAL_VIDEO, HTTP_AUDIO, HTTP_VIDEO, PLAYLIST;
}

data class PlayerState(var window: Int = 0,
                       var position: Long = 0,
                       var whenReady: Boolean = true,
                       var sourceType: SourceType = SourceType.HTTP_VIDEO)

class VideoService(val context: Context,
                   val playerView: PlayerView,
                   val playerState: PlayerState) : AnkoLogger {
    val player: ExoPlayer

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
        player.prepare(buildMediaSource(selectMediaToPlay(playerState.sourceType)))
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

    fun selectMediaToPlay(sourceType: SourceType): Uri {
        return when (sourceType) {
            SourceType.LOCAL_AUDIO -> Uri.parse("asset:///audio/file.mp3")
            SourceType.LOCAL_VIDEO -> Uri.parse("asset:///video.mp4")
            SourceType.HTTP_AUDIO -> Uri.parse("http://site.../file.mp3")
            SourceType.HTTP_VIDEO -> Uri.parse("http://techslides.com/demos/sample-videos/small.mp4")
            else -> throw Exception("Something wrong with sourceType")
        }
    }

    private fun buildMediaSource(uri: Uri): ExtractorMediaSource {
        return ExtractorMediaSource.Factory(
            DefaultDataSourceFactory(context, "videoapp")
        ).createMediaSource(uri)
    }
}