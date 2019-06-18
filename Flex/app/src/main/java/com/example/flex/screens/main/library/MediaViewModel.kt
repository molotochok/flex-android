package com.example.flex.screens.main.library

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.flex.db.entity.Media
import com.example.flex.db.repository.MediaRepository

class MediaViewModel(application: Application) : AndroidViewModel(application) {
    val repository : MediaRepository = MediaRepository(application)
    private val allMedia : LiveData<List<Media>>

    init {
        allMedia = repository.getAllMedia()
    }

    fun getAllMedia() : LiveData<List<Media>> {
        return allMedia
    }
}