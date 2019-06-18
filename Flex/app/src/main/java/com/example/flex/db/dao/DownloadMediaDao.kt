package com.example.flex.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.flex.db.entity.DownloadMedia
@Dao
abstract class DownloadMediaDao {
    @Insert
    abstract fun insert(downloadMedia : DownloadMedia)

    @Update
    abstract fun update(downloadMedia : DownloadMedia)

    @Delete
    abstract fun delete(downloadMedia : DownloadMedia)

    @Transaction
    @Query("DELETE FROM download_media_table")
    abstract fun deleteAll()

    @Insert
    abstract fun insertAll(downloadMedia: List<DownloadMedia>)

    @Transaction
    @Query("SELECT * FROM download_media_table")
    abstract fun getAllDownloadMedia() : LiveData<List<DownloadMedia>>

    @Query("SELECT * FROM download_media_table WHERE id = :id")
    abstract fun getDownloadMediaById(id: Int) : LiveData<DownloadMedia>

    @Transaction
    open fun replaceAll(downloadMedia: List<DownloadMedia>) {
        deleteAll()
        insertAll(downloadMedia)
    }
}