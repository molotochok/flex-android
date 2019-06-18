package com.example.flex.db

import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.example.flex.FlexApplication
import okhttp3.HttpUrl
import com.example.flex.common.Utils

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
            val host = Utils.readSharedSetting(FlexApplication.applicationContext(), Utils.PREF_HOSTNAME_KEY, "localhost")!!
            val port = Utils.readSharedSetting(FlexApplication.applicationContext(), Utils.PREF_PORT_KEY, "8080")!!.toInt()
            
            return HttpUrl.Builder()
                .scheme("http")
                .host(host)
                .port(port)
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