package com.example.flex.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profiles_table")
data class Profiles (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name : String
)