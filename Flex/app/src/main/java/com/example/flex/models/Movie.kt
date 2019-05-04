package com.example.flex.models

data class Movie (
    val id: Int,
    val name: String,
    val duration : Double,
    val resolution : String,
    val size: String,
    val posterPath: String,
    override val mediaType: MediaType = MediaType.MOVIE
) : Media(mediaType)