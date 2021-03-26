package ru.myproject.android_2_kotlin.utils

import ru.myproject.android_2_kotlin.model.FactDTO
import ru.myproject.android_2_kotlin.model.Weather
import ru.myproject.android_2_kotlin.model.WeatherDTO
import ru.myproject.android_2_kotlin.model.getDefaultCity

fun convertDtoToModel(weatherDTO: WeatherDTO): List<Weather> {
    val fact: FactDTO = weatherDTO.fact!!
    return listOf(
        Weather(
            getDefaultCity(),
            fact.temp!!,
            fact.feels_like!!,
            fact.condition!!,
            fact.icon
        )
    )
}