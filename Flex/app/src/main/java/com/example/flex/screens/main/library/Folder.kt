package com.example.flex.screens.main.library

data class Folder (
    val id: Int,
    val name: String,
    val posterPath: String,
    override val mediaType: MediaType = MediaType.FOLDER
) : Media(mediaType)