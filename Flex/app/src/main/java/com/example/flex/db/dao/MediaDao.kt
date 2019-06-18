package com.example.flex.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.flex.db.entity.Media

@Dao
abstract class MediaDao {
    @Insert
    abstract fun insert(media : Media)

    @Update
    abstract fun update(media : Media)

    @Delete
    abstract fun delete(media : Media)

    @Transaction
    @Query("DELETE FROM media_table")
    abstract fun deleteAll()

    @Insert
    abstract fun insertAll(media: List<Media>)

    @Transaction
    @Query("SELECT * FROM media_table")
    abstract fun getAllMedia() : LiveData<List<Media>>

    @Query("SELECT * FROM media_table WHERE id = :id")
    abstract fun getMediaById(id: Int) : LiveData<Media>

    @Transaction
    open fun replaceAll(media: List<Media>) {
        deleteAll()
        insertAll(media)
    }
}