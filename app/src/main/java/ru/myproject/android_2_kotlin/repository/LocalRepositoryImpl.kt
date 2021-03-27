package ru.myproject.android_2_kotlin.repository

import ru.myproject.android_2_kotlin.model.Weather
import ru.myproject.android_2_kotlin.room.HistoryDao
import ru.myproject.android_2_kotlin.utils.convertHistoryEntityToWeather
import ru.myproject.android_2_kotlin.utils.convertWeatherToEntity

class LocalRepositoryImpl(private val localDataSource: HistoryDao) : LocalRepository {
    override fun getAllHistory(): List<Weather> {
        return convertHistoryEntityToWeather(localDataSource.all())
    }

    override fun saveEntity(weather: Weather) {
        localDataSource.insert(convertWeatherToEntity(weather))
    }
}