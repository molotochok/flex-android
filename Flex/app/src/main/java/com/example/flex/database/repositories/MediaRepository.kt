package com.example.flex.database.repositories

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.apollographql.apollo.exception.ApolloHttpException
import com.example.flex.MediaQuery
import com.example.flex.database.FlexClient
import com.example.flex.database.FlexDatabase
import com.example.flex.database.daos.MediaDao
import com.example.flex.database.entities.Media

class MediaRepository(application: Application) {
    private val mediaDao: MediaDao
    private val allMedia: LiveData<List<Media>>

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

    fun getAllMedia() : LiveData<List<Media>>{
        refreshAllMedia()
        return allMedia
    }

    private fun refreshAllMedia() {
        val apollo = FlexClient.getClient()

        apollo.query(
            MediaQuery(Input.absent())
        ).enqueue(object : ApolloCall.Callback<MediaQuery.Data>() {
            override fun onResponse(response: Response<MediaQuery.Data>) {
                val updatedMedia = ArrayList<Media>()

                for (media in response.data()?.media!!) {
                    val entity = Media(
                        media!!.id,
                        media.name,
                        media.path,
                        media.duration,
                        media.created,
                        media.last_seen,
                        media.time_point,
                        media.status,
                        media.width,
                        media.heigth,
                        media.size,
                        media.thumbnail.toByteArray()
                    )

                    updatedMedia.add(entity)
                }

                mediaDao.replaceAll(updatedMedia)
            }

            override fun onFailure(e: ApolloException) {
                val ee = e as ApolloHttpException
                Log.e("kek", ee.rawResponse()?.body()?.string(), e)
            }
        })

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
