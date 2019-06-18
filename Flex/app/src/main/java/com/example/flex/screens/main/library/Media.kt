package com.example.flex.screens.main.library

enum class MediaType (val value : Int){
    FOLDER(1),
    MOVIE(2)
}

open class Media (open val mediaType : MediaType)