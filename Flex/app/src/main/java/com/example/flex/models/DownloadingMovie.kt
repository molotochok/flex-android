package com.example.flex.models


// For representing movie which are currently downloading
data class DownloadingMovie(
    val id : Int,
    val name : String,
    val speed : Float,
    val speedType : String,
    val currSize : Float,
    val currSizeType : String,
    val maxSize : Float,
    val maxSizeType : String,
    val timeLeft : Int,
    val timeLeftType : String,
    val posterPath : String,
    val progress : Int,
    override val downloadStatus : DownloadStatus
) : DownloadMedia(downloadStatus)