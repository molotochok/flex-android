package com.example.flex.database

import com.apollographql.apollo.ApolloClient
import okhttp3.HttpUrl

class FlexClient {
    companion object {
        fun getClient(): ApolloClient {
            val flexUrl: HttpUrl = HttpUrl.Builder()
                .host("localhost")
                .port(8080)
                .build()

            return ApolloClient.builder().serverUrl(flexUrl).build()
        }
    }
}