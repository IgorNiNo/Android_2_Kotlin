package ru.myproject.android_2_kotlin.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import ru.myproject.android_2_kotlin.model.WeatherDTO

interface WeatherAPI {
    // когда тариф "Тестовый" закончится, необходимо будет в запросе forecast поменять на informers
    @GET("v2/forecast")
    fun getWeather(
        @Header("X-Yandex-API-Key") token: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Call<WeatherDTO>
}