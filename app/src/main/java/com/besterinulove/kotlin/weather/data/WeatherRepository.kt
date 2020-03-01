package com.besterinulove.kotlin.weather.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.besterinulove.kotlin.weather.api.WeatherService
import com.besterinulove.kotlin.weather.db.WeatherDatabase
import com.besterinulove.kotlin.weather.db.entity.WeatherElementEntity
import com.besterinulove.kotlin.weather.db.entity.WeatherLocationEntity

class WeatherRepository(
    private val service: WeatherService,
    private val database: WeatherDatabase
) {
    private fun locationDao() = database.getWeatherLocationDao()
    private fun elementDao() = database.getWeatherElementDao()

    suspend fun searchWeather(
        limit: Int = 1,
        locationName: String? = null,
        elementName: String? = null
    ) {
        val response = service.getWeather(
            limit = limit,
            locationName = locationName,
            elementName = elementName
        )
        response.records.location.forEach { location ->
            locationDao().insert(WeatherLocationEntity.instance(location))
            location.weatherElement.forEach { element ->
               val elements= element.time.map {
                    WeatherElementEntity.instance(
                        location.locationName,
                        element.elementName,
                        it
                    )
                }.toTypedArray()
                elementDao().insert(*elements)
            }
        }
    }

    fun getWeatherByLocationName(locationName: String) =
        database.getWeatherLocationDao().getWeatherLocationLiveData(locationName)

    suspend fun getAll(): LiveData<Boolean> = liveData {
        emit(database.getWeatherLocationDao().getAll().isEmpty())
    }
}