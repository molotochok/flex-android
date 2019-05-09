package com.example.flex.models

enum class SettingsType(val value: Int){
    ServerConnection(value = 1),
    About(value = 2)

}

data class Settings(
    val id: Int,
    val name: String,
    val iconDrawableId: Int,
    val settingsType: SettingsType
)