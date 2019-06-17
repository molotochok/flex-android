package com.example.flex.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.flex.database.entities.Media

@Dao
interface MediaDao {
    @Insert
    fun insert(media : Media)

    @Update
    fun update(media : Media)

    @Delete
    fun delete(media : Media)

    @Query("SELECT * FROM media_table")
    fun getAllMedia() : LiveData<ArrayList<Media>>
}