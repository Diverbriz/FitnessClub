package com.example.kotlinlesson.domain.model

import java.util.UUID

class Slider constructor( var backgroundImg: Int,
                 var title: String,  var description: String) {
    private var id: String
        get() {
            return id
        }
        set(value) {}

    private fun randomUUID() = UUID.randomUUID().toString()
}