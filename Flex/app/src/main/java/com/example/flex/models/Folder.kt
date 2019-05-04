package com.example.flex.models

data class Folder (
    val id: Int,
    val name: String,
    val posterPath: String,
    override val mediaType: MediaType = MediaType.FOLDER
) : Media(mediaType)