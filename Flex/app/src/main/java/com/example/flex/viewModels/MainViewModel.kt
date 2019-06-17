package com.example.flex.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.flex.database.entities.Media
import com.example.flex.database.repositories.MediaRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val repository : MediaRepository = MediaRepository(application)
    private val allMedia : LiveData<List<Media>>

    init {
        allMedia = repository.getAllMedia()
    }

    fun getAllMedia() : LiveData<List<Media>> {
        return allMedia
    }
}