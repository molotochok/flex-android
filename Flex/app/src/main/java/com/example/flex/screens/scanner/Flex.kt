package com.example.flex.screens.scanner

class Flex {
    var name: String ?= null
    var host: String ?= null
    var port: Int ?= null

    override fun toString(): String {
        return "name: $name, host: $host, port: $port"
    }
}