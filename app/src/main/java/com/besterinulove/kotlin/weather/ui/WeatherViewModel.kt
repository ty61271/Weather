package com.besterinulove.kotlin.weather.ui

import androidx.lifecycle.*
import com.besterinulove.kotlin.weather.data.WeatherRepository
import com.besterinulove.kotlin.weather.model.Locations
import com.besterinulove.kotlin.weather.model.WeatherElements
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    lateinit var isEmpty: LiveData<Boolean>

    init {
        viewModelScope.launch {
            isEmpty = weatherRepository.getAll()
            weatherRepository.searchWeather(
                20,
                Locations.TaiPei.locationName,
                WeatherElements.MinT.weatherElement
            )
        }
    }

    private val location = MutableLiveData<String>()
    val weather = location.switchMap {
        weatherRepository.getWeatherByLocationName(it)
    }

    fun getWeatherByLocationName(locationName: String) {
        location.value = locationName
    }
}