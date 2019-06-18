package com.example.flex.screens.main.library


data class Movie (
    val id: Int,
    val name: String,
    val duration : String,
    val resolution : String,
    val size: String,
    val thumbnail: ByteArray,
    override val mediaType: MediaType = MediaType.MOVIE
) : Media(mediaType) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movie

        if (id != other.id) return false
        if (name != other.name) return false
        if (duration != other.duration) return false
        if (resolution != other.resolution) return false
        if (size != other.size) return false
        if (!thumbnail.contentEquals(other.thumbnail)) return false
        if (mediaType != other.mediaType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + duration.hashCode()
        result = 31 * result + resolution.hashCode()
        result = 31 * result + size.hashCode()
        result = 31 * result + thumbnail.contentHashCode()
        result = 31 * result + mediaType.hashCode()
        return result
    }
}