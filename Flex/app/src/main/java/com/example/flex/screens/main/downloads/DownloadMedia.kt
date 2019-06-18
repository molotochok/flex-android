package com.example.flex.screens.main.downloads

enum class DownloadStatus(val value: Int) {
    Downloaded(1),
    Downloading(2)
}

open class DownloadMedia (open val downloadStatus : DownloadStatus)