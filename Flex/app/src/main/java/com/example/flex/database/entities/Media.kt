package com.example.flex.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "media_table")
data class Media(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val path: String,
    val duration : Int,
    val created : Int,
    val lastSeen : Int,
    val timePoint : Int,
    val status : String
)