package com.example.flex.database.repositories

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.flex.database.FlexDatabase
import com.example.flex.database.daos.ProfilesDao
import com.example.flex.database.entities.Profiles


class ProfilesRepository(application: Application) {
    private val profilesDao: ProfilesDao
    private val allProfiles: LiveData<ArrayList<Profiles>>

    init {
        val database = FlexDatabase.getInstance(application)
        profilesDao = database!!.profilesDao()
        allProfiles = profilesDao.getAllProfiles()
    }

    fun insert(profiles : Profiles){
        InsertProfilesAsyncTask(profilesDao).execute(profiles)
    }

    fun update(profiles : Profiles){
        UpdateProfilesAsyncTask(profilesDao).execute(profiles)
    }

    fun delete(profiles : Profiles){
        DeleteProfilesAsyncTask(profilesDao).execute(profiles)
    }

    fun getAllProfiles() : LiveData<ArrayList<Profiles>> {
        return allProfiles
    }

    // AsyncTasks
    private class InsertProfilesAsyncTask internal constructor(private val profilesDao: ProfilesDao) :
        AsyncTask<Profiles, Void, Void>() {

        override fun doInBackground(vararg profiles: Profiles): Void? {
            profilesDao.insert(profiles[0])
            return null
        }
    }
    private class UpdateProfilesAsyncTask internal constructor(private val profilesDao: ProfilesDao) :
        AsyncTask<Profiles, Void, Void>() {

        override fun doInBackground(vararg profiles: Profiles): Void? {
            profilesDao.update(profiles[0])
            return null
        }
    }
    private class DeleteProfilesAsyncTask internal constructor(private val profilesDao: ProfilesDao) :
        AsyncTask<Profiles, Void, Void>() {

        override fun doInBackground(vararg profiles: Profiles): Void? {
            profilesDao.delete(profiles[0])
            return null
        }
    }
}