package com.example.flex.models

enum class MediaType (val value : Int){
    FOLDER(1),
    MOVIE(2)
}

open class Media (open val mediaType : MediaType)