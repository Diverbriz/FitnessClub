package com.example.kotlinlesson.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinlesson.R
import com.example.kotlinlesson.domain.model.Course
import com.example.kotlinlesson.domain.model.Slider

class HomeViewModel : ViewModel() {
    val slider: MutableLiveData<List<Slider>> by lazy {
        MutableLiveData<List<Slider>>()
    }

    private var backgroundImg: List<Int> = arrayListOf(
    R.drawable.main_crossfit,
    R.drawable.woman_in_gym, R.drawable.atlet_slider)

    val coursesItems: MutableLiveData<List<Course>> by lazy {
        MutableLiveData<List<Course>>()
    }

    val currentState: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    init {
        currentState.value = 0

        slider.value = arrayListOf(Slider(backgroundImg[0], "Title", "Description"),
            Slider(backgroundImg[1], "Title", "Description"),
            Slider(backgroundImg[2], "Title", "Description"))

        coursesItems.value = arrayListOf(
            Course(R.drawable.personal_trainer.toString(), "Fitness", "Personal Trainer"),
            Course(R.drawable.sport_eat.toString(), "Nutrition", "Sport Nutrition"),
            Course(backgroundImg[2].toString(), "Fitness", "Personal Trainer"))
    }
}