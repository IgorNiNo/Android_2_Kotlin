package ru.myproject.android_2_kotlin.repository

import ru.myproject.android_2_kotlin.model.WeatherDTO

interface DetailsRepository {
    fun getWeatherDetailsFromServer(
        lat: Double,
        lon: Double,
        callback: retrofit2.Callback<WeatherDTO>
    )
}