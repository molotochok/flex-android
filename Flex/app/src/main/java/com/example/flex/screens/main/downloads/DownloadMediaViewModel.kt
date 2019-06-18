package com.example.flex.screens.main.downloads

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.flex.db.entity.DownloadMedia
import com.example.flex.db.repository.DownloadMediaRepository

class DownloadMediaViewModel(application: Application) : AndroidViewModel(application) {
    val repository : DownloadMediaRepository = DownloadMediaRepository(application)
    private val allDownloadMedia : LiveData<List<DownloadMedia>>
    val error: MutableLiveData<Boolean?>

    init {
        allDownloadMedia = repository.getAllDownloadMedia()
        error = repository.error
    }

    fun getAllDownloadMedia() : LiveData<List<DownloadMedia>> {
        return allDownloadMedia
    }
}