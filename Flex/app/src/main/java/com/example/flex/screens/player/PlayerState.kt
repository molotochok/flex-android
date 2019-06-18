package com.example.flex.screens.player

data class PlayerState(var window: Int = 0,
                       var position: Long = 0,
                       var whenReady: Boolean = true,
                       var sourceType: SourceType = SourceType.HTTP_VIDEO
)