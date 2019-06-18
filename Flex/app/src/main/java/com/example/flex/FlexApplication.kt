package com.example.flex

import android.app.Application
import android.content.Context


class FlexApplication: Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: FlexApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        // initialize for any

        // Use ApplicationContext.
        // example: SharedPreferences etc...
        val context: Context = applicationContext
    }
}