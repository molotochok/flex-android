package com.example.flex.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.flex.database.entities.Profiles
import com.example.flex.database.entities.ViewingInfo

@Dao
interface ViewingInfoDao {
    @Insert
    fun insert(viewingInfo : ViewingInfo)

    @Update
    fun update(viewingInfo : ViewingInfo)

    @Delete
    fun delete(viewingInfo : ViewingInfo)

    @Query("SELECT * FROM viewing_info_table")
    fun getAllViewingInfo() : LiveData<ArrayList<ViewingInfo>>
}