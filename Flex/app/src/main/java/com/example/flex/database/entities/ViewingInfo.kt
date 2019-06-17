package com.example.flex.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "viewing_info_table")
data class ViewingInfo (
    @PrimaryKey(autoGenerate = true)
    val viewingInfoId : Int,
    val mediaId : Int,
    val profileId : Int,
    val timePoint : Int,
    val timeStamp : Int
)