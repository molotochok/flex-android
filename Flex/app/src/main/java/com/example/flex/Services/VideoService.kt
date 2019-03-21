package com.example.flex.Services

import android.content.Context
import android.net.Uri
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.source.DynamicConcatenatingMediaSource
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


enum class SourceType {
    LOCAL_AUDIO, LOCAL_VIDEO, HTTP_AUDIO, HTTP_VIDEO, PLAYLIST;
}

data class PlayerState(var window: Int = 0,
                       var position: Long = 0,
                       var whenReady: Boolean = true,
                       var sourceType: SourceType = SourceType.PLAYLIST)

class VideoService(val context: Context,
                   val playerView: PlayerView,
                   val playerState: PlayerState) : AnkoLogger {


    // fields
    private val mediaMap = mapOf<SourceType, Uri>(
        SourceType.LOCAL_AUDIO to Uri.parse("asset:///audio.mp3"),
        SourceType.LOCAL_VIDEO to Uri.parse("asset:///video.mp4"),
        SourceType.HTTP_AUDIO to Uri.parse("https://ccrma.stanford.edu/~jos/mp3/gtr-wah.mp3"),
        SourceType.HTTP_VIDEO to Uri.parse("http://techslides.com/demos/sample-videos/small.mp4")
    )
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
        player.prepare(buildMediaSource(playerState.sourceType))
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

    fun buildMediaSource(sourceType: SourceType): MediaSource {
        return when (sourceType) {
            SourceType.PLAYLIST -> {
                val source = DynamicConcatenatingMediaSource();

                source.addMediaSource(createExtractorMediaSource(SourceType.LOCAL_AUDIO))
                source.addMediaSource(createExtractorMediaSource(SourceType.LOCAL_VIDEO))
                source.addMediaSource(createExtractorMediaSource(SourceType.HTTP_AUDIO))
                source.addMediaSource(createExtractorMediaSource(SourceType.HTTP_VIDEO))

                source
            }
            else -> {
                createExtractorMediaSource(sourceType)
            }
        }
    }


    // Private methods
    private fun createExtractorMediaSource(sourceType: SourceType): MediaSource {
        return ExtractorMediaSource.Factory(DefaultDataSourceFactory(context, "flex"))
            .createMediaSource(mediaMap[sourceType])
    }
}