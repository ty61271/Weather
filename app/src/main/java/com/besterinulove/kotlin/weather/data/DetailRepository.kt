package com.besterinulove.kotlin.weather.data

import com.besterinulove.kotlin.weather.db.WeatherDatabase

class DetailRepository(
    private val database: WeatherDatabase
) {
    private fun elementDao() = database.getWeatherElementDao()

    suspend fun getElementWithIndex(index: Int)= elementDao().getElementWithIndex(index)
}