package com.example.flex.db

import android.app.Application
import android.util.Log
import com.apollographql.apollo.ApolloClient
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

        fun getStreamingUrl(mediaId: Int): HttpUrl {
            val flexUrl = flexServerUrl()
            return flexUrl.newBuilder()
                .addPathSegment("videos")
                .addPathSegment(mediaId.toString())
                .build()
        }
    }
}