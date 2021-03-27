package ru.myproject.android_2_kotlin.app

import ru.myproject.android_2_kotlin.model.Weather

sealed class AppState {
    data class Success(val weatherData: List<Weather>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}