package com.example.flex.database.daos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.flex.database.entities.Media
import java.sql.RowId

@Dao
abstract class MediaDao {
    @Insert
    abstract fun insert(media : Media)

    @Update
    abstract fun update(media : Media)

    @Delete
    abstract fun delete(media : Media)

    @Query("DELETE FROM media_table")
    abstract fun deleteAll()

    @Insert
    abstract fun insertAll(media: List<Media>)

    @Query("SELECT * FROM media_table")
    abstract fun getAllMedia() : LiveData<ArrayList<Media>>

    @Query("SELECT * FROM media_table WHERE id = :id")
    abstract fun getMediaById(id: Int) : LiveData<Media>

    @Transaction
    fun replaceAll(media: List<Media>) {
        deleteAll()
        insertAll(media)
    }
}