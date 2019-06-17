package com.example.flex.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "media_table")
data class Media(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val path: String,
    val duration: Int,
    val created: Int,
    val lastSeen: Int,
    val timePoint: Int,
    val status: String,
    val width: Int,
    val height: Int,
    val size: Int,
    val thumbnail: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Media

        if (id != other.id) return false
        if (name != other.name) return false
        if (path != other.path) return false
        if (duration != other.duration) return false
        if (created != other.created) return false
        if (lastSeen != other.lastSeen) return false
        if (timePoint != other.timePoint) return false
        if (status != other.status) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (size != other.size) return false
        if (!thumbnail.contentEquals(other.thumbnail)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + path.hashCode()
        result = 31 * result + duration
        result = 31 * result + created
        result = 31 * result + lastSeen
        result = 31 * result + timePoint
        result = 31 * result + status.hashCode()
        result = 31 * result + width
        result = 31 * result + height
        result = 31 * result + size
        result = 31 * result + thumbnail.contentHashCode()
        return result
    }
}