package com.example.flex.screens.main.downloads

data class DownloadMovie(
    val id: Int,
    val name: String,
    val size: String,
    val thumbnail: ByteArray,
    override val downloadStatus: DownloadStatus
) : DownloadMedia(downloadStatus) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DownloadMovie

        if (id != other.id) return false
        if (name != other.name) return false
        if (size != other.size) return false
        if (!thumbnail.contentEquals(other.thumbnail)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + size.hashCode()
        result = 31 * result + thumbnail.contentHashCode()
        return result
    }
}