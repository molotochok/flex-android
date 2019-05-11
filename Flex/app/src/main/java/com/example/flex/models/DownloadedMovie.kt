package com.example.flex.models

// For representing movies which are already downloaded
data class DownloadedMovie(
    val id : Int,
    val name : String,
    val currSize : Float,
    val currSizeType : String,
    val maxSize : Float,
    val maxSizeType : String,
    val posterPath : String,
    override val downloadStatus : DownloadStatus
) : DownloadMedia(downloadStatus)