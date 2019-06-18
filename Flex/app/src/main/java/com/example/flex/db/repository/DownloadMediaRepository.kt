package com.example.flex.db.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.flex.db.FlexDatabase
import com.example.flex.db.dao.DownloadMediaDao
import com.example.flex.db.entity.DownloadMedia

class DownloadMediaRepository(application: Application) {
    private val downloadMediaDao: DownloadMediaDao
    private val allDownloadMedia: LiveData<List<DownloadMedia>>
    val error: MutableLiveData<Boolean?>

    init {
        val database = FlexDatabase.getInstance(application)
        downloadMediaDao = database!!.downloadMediaDao()
        allDownloadMedia = downloadMediaDao.getAllDownloadMedia()
        error = MutableLiveData()
    }

    fun insert(downloadMedia : DownloadMedia){
        InsertDownloadMediaAsyncTask(downloadMediaDao).execute(downloadMedia)
    }

    fun update(downloadMedia : DownloadMedia){
        UpdateDownloadMediaAsyncTask(downloadMediaDao).execute(downloadMedia)
    }

    fun delete(downloadMedia : DownloadMedia){
        DeleteDownloadMediaAsyncTask(downloadMediaDao).execute(downloadMedia)
    }

    fun getAllDownloadMedia() : LiveData<List<DownloadMedia>> {
        return allDownloadMedia
    }

    // AsyncTasks
    private class InsertDownloadMediaAsyncTask internal constructor(private val downloadMediaDao: DownloadMediaDao) :
        AsyncTask<DownloadMedia, Void, Void>() {

        override fun doInBackground(vararg downloadMedia: DownloadMedia): Void? {
            downloadMediaDao.insert(downloadMedia[0])
            return null
        }
    }
    private class UpdateDownloadMediaAsyncTask internal constructor(private val downloadMediaDao: DownloadMediaDao) :
        AsyncTask<DownloadMedia, Void, Void>() {

        override fun doInBackground(vararg downloadMedia: DownloadMedia): Void? {
            downloadMediaDao.update(downloadMedia[0])
            return null
        }
    }
    private class DeleteDownloadMediaAsyncTask internal constructor(private val downloadMediaDao: DownloadMediaDao) :
        AsyncTask<DownloadMedia, Void, Void>() {

        override fun doInBackground(vararg downloadMedia: DownloadMedia): Void? {
            downloadMediaDao.delete(downloadMedia[0])
            return null
        }
    }
}
