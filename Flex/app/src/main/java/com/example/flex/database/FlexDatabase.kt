package com.example.flex.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.flex.database.daos.MediaDao
import com.example.flex.database.daos.ProfilesDao
import com.example.flex.database.daos.ViewingInfoDao
import com.example.flex.database.entities.Media
import com.example.flex.database.entities.Profiles
import com.example.flex.database.entities.ViewingInfo


@Database(entities = [Media::class, Profiles::class, ViewingInfo::class], version = 1)
abstract class FlexDatabase : RoomDatabase() {
    private var instance: FlexDatabase? = null

    abstract fun mediaDao() : MediaDao
    abstract fun profilesDao() : ProfilesDao
    abstract fun viewingInfoDao() : ViewingInfoDao

    @Synchronized
    fun getInstance(context : Context): FlexDatabase?
    {
        if(instance == null){
            instance = Room.databaseBuilder(context.applicationContext, FlexDatabase::class.java, "flex_database")
                .fallbackToDestructiveMigration()
                .build()

        }
        return instance
    }
}