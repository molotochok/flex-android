package com.example.flex.database

import com.apollographql.apollo.ApolloClient
import com.example.flex.database.entities.Media
import okhttp3.HttpUrl

class FlexClient {
    companion object {
        fun getClient(): ApolloClient {
            val flexUrl: HttpUrl = flexServerUrl()

            // FIXME: replace with settings

            return ApolloClient.builder().serverUrl(flexUrl).build()
        }

        private fun flexServerUrl(): HttpUrl {
            return HttpUrl.Builder()
                .host("localhost")
                .port(8080)
                .build()
        }

        fun getStreamingUrl(media: Media): HttpUrl {
            val flexUrl = flexServerUrl()
            return flexUrl.newBuilder()
                .addPathSegment("video")
                .addPathSegments(media.id.toString())
                .build()
        }
    }
}