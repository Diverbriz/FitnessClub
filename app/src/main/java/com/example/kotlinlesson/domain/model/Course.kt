package com.example.kotlinlesson.domain.model

import java.time.LocalDate

class Course(var url: String, var title: String, var description:String) {
    private var rating:Float = 0.0f
    get() = if(field < 0.0f) 0.0f else field
    set(value) {
        field = if(value < 0) 0.0f else value
    }
}