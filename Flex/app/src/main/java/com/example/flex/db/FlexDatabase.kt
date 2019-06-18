package com.example.flex.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.flex.db.dao.MediaDao
import com.example.flex.db.entity.Media


@Database(entities = [Media::class], version = 1)
abstract class FlexDatabase : RoomDatabase() {
    abstract fun mediaDao() : MediaDao

    companion object {
        private var instance: FlexDatabase? = null
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
}