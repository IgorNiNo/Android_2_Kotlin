package ru.myproject.android_2_kotlin.repository

import ru.myproject.android_2_kotlin.model.Weather
import ru.myproject.android_2_kotlin.model.getRussianCities
import ru.myproject.android_2_kotlin.model.getWorldCities

class MainRepositoryImpl : MainRepository {

    override fun getWeatherFromServer() = Weather()

    override fun getWeatherFromLocalStorageRus() = getRussianCities()

    override fun getWeatherFromLocalStorageWorld() = getWorldCities()
}