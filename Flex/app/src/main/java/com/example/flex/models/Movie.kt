package com.example.flex.models

import java.time.Duration

data class Movie (
    val index: Int,
    val name: String,
    val duration : Double,
    val resolution : String,
    val size: String,
    val posterPath: String
)