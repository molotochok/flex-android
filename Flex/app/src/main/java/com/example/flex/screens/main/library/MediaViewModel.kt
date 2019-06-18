package com.example.flex.screens.main.library

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.flex.db.entity.Media
import com.example.flex.db.repository.MediaRepository

class MediaViewModel(application: Application) : AndroidViewModel(application) {
    val repository : MediaRepository = MediaRepository(application)
    private val allMedia : LiveData<List<Media>>
    val error: MutableLiveData<Boolean?>

    init {
        allMedia = repository.getAllMedia()
        error = repository.error
    }

    fun getAllMedia() : LiveData<List<Media>> {
        return allMedia
    }

    fun refresh() {
        repository.refreshAllMedia()
    }
}