package com.example.flex.database.repositories

import com.example.flex.database.daos.MediaDao
import android.app.Application
import androidx.lifecycle.LiveData
import com.example.flex.database.entities.Media
import android.os.AsyncTask
import com.apollographql.apollo.api.Input
import com.example.flex.MediaQuery
import com.example.flex.database.FlexClient
import com.example.flex.database.FlexDatabase

class MediaRepository(application: Application) {
    private val mediaDao: MediaDao
    private val allMedia: LiveData<ArrayList<Media>>

    init {
        val database = FlexDatabase.getInstance(application)
        mediaDao = database!!.mediaDao()
        allMedia = mediaDao.getAllMedia()
    }

    fun insert(media : Media){
        InsertMediaAsyncTask(mediaDao).execute(media)
    }

    fun update(media : Media){
        UpdateMediaAsyncTask(mediaDao).execute(media)
    }

    fun delete(media : Media){
        DeleteMediaAsyncTask(mediaDao).execute(media)
    }

    fun getAllMedia() : LiveData<ArrayList<Media>>{
        refreshAllMedia()
        return allMedia
    }

    private fun refreshAllMedia() {
        val apollo = FlexClient.getClient()

        apollo.query(
            MediaQuery(Input.absent())
        )

        // TODO: finish this
    }


    // AsyncTasks
    private class InsertMediaAsyncTask internal constructor(private val mediaDao: MediaDao) :
        AsyncTask<Media, Void, Void>() {

        override fun doInBackground(vararg media: Media): Void? {
            mediaDao.insert(media[0])
            return null
        }
    }
    private class UpdateMediaAsyncTask internal constructor(private val mediaDao: MediaDao) :
        AsyncTask<Media, Void, Void>() {

        override fun doInBackground(vararg media: Media): Void? {
            mediaDao.update(media[0])
            return null
        }
    }
    private class DeleteMediaAsyncTask internal constructor(private val mediaDao: MediaDao) :
        AsyncTask<Media, Void, Void>() {

        override fun doInBackground(vararg media: Media): Void? {
            mediaDao.delete(media[0])
            return null
        }
    }
}
