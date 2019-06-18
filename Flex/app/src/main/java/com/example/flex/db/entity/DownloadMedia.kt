package com.example.flex.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "download_media_table")
data class DownloadMedia(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val filePath: String,
    val status: Int,
    val size: Int,
    val thumbnail: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DownloadMedia

        if (id != other.id) return false
        if (name != other.name) return false
        if (filePath != other.filePath) return false
        if (status != other.status) return false
        if (size != other.size) return false
        if (!thumbnail.contentEquals(other.thumbnail)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + filePath.hashCode()
        result = 31 * result + status.hashCode()
        result = 31 * result + size
        result = 31 * result + thumbnail.contentHashCode()
        return result
    }
}