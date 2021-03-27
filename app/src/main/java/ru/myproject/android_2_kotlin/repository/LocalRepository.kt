package ru.myproject.android_2_kotlin.repository

import ru.myproject.android_2_kotlin.model.Weather

interface LocalRepository {
    fun getAllHistory(): List<Weather>
    fun saveEntity(weather: Weather)
}