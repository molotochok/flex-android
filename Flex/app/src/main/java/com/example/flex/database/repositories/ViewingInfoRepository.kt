package com.example.flex.database.repositories

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.flex.database.FlexDatabase
import com.example.flex.database.daos.ViewingInfoDao
import com.example.flex.database.entities.ViewingInfo

class ViewingInfoRepository(application: Application) {
    private val pviewingInfoDao: ViewingInfoDao
    private val allViewingInfo: LiveData<ArrayList<ViewingInfo>>

    init {
        val database = FlexDatabase.getInstance(application)
        pviewingInfoDao = database!!.viewingInfoDao()
        allViewingInfo = pviewingInfoDao.getAllViewingInfo()
    }

    fun insert(pviewingInfo : ViewingInfo){
        InsertViewingInfoAsyncTask(pviewingInfoDao).execute(pviewingInfo)
    }

    fun update(pviewingInfo : ViewingInfo){
        UpdateViewingInfoAsyncTask(pviewingInfoDao).execute(pviewingInfo)
    }

    fun delete(pviewingInfo : ViewingInfo){
        DeleteViewingInfoAsyncTask(pviewingInfoDao).execute(pviewingInfo)
    }

    fun getAllViewingInfo() : LiveData<ArrayList<ViewingInfo>> {
        return allViewingInfo
    }

    // AsyncTasks
    private class InsertViewingInfoAsyncTask internal constructor(private val pviewingInfoDao: ViewingInfoDao) :
        AsyncTask<ViewingInfo, Void, Void>() {

        override fun doInBackground(vararg pviewingInfo: ViewingInfo): Void? {
            pviewingInfoDao.insert(pviewingInfo[0])
            return null
        }
    }
    private class UpdateViewingInfoAsyncTask internal constructor(private val pviewingInfoDao: ViewingInfoDao) :
        AsyncTask<ViewingInfo, Void, Void>() {

        override fun doInBackground(vararg pviewingInfo: ViewingInfo): Void? {
            pviewingInfoDao.update(pviewingInfo[0])
            return null
        }
    }
    private class DeleteViewingInfoAsyncTask internal constructor(private val pviewingInfoDao: ViewingInfoDao) :
        AsyncTask<ViewingInfo, Void, Void>() {

        override fun doInBackground(vararg pviewingInfo: ViewingInfo): Void? {
            pviewingInfoDao.delete(pviewingInfo[0])
            return null
        }
    }
}