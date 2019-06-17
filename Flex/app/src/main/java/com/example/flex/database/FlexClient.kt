package com.example.flex.database

import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.example.flex.database.entities.Media
import okhttp3.HttpUrl

class FlexClient {
    companion object {
        fun getClient(): ApolloClient {
            val flexUrl: HttpUrl = flexServerUrl().newBuilder()
                .addPathSegment("query")
                .build()

            Log.i("kek", flexUrl.toString())

            // FIXME: replace with settings

            return ApolloClient.builder()
                .serverUrl(flexUrl)
                .useHttpGetMethodForQueries(true)
                .build()
        }

        private fun flexServerUrl(): HttpUrl {
            return HttpUrl.Builder()
                .scheme("http")
                .host("192.168.88.238")
                .port(8080)
                .build()
        }

        fun getStreamingUrl(media: Media): HttpUrl {
            val flexUrl = flexServerUrl()
            return flexUrl.newBuilder()
                .addPathSegment("video")
                .addPathSegment(media.id.toString())
                .build()
        }
    }
}