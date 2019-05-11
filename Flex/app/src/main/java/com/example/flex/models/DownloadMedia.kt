package com.example.flex.models

enum class DownloadStatus(val value: Int) {
    Downloaded(1),
    Downloading(2)
}

open class DownloadMedia (open val downloadStatus : DownloadStatus)