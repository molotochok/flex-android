package com.example.flex.models

enum class MediaType (val value : Int){
    Folder(1),
    Movie(2)
}

open class Media (open val mediaType : MediaType)