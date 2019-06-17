package com.example.flex.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.flex.database.entities.Profiles

@Dao
interface ProfilesDao {
    @Insert
    fun insert(profiles : Profiles)

    @Update
    fun update(profiles : Profiles)

    @Delete
    fun delete(profiles : Profiles)

    @Query("SELECT * FROM profiles_table")
    fun getAllProfiles() : LiveData<ArrayList<Profiles>>
}