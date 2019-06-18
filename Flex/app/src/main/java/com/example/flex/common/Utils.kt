package com.example.flex.common

import android.content.Context


object Utils {
    private val PREFERENCES_FILE = "flex_settings"

    val PREF_USER_FIRST_TIME = "user_first_time"
    val PREF_HOSTNAME_KEY = "hostname_key"
    val PREF_PORT_KEY = "port_key"
    val PREF_DISPLAY_NAME_KEY = "display_name_key"

    fun readSharedSetting(ctx: Context, settingName: String, defaultValue: String): String? {
        val sharedPref = ctx.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)
        return sharedPref.getString(settingName, defaultValue)
    }

    fun saveSharedSetting(ctx: Context, settingName: String, settingValue: String) {
        val sharedPref = ctx.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(settingName, settingValue)
        editor.apply()
    }
}