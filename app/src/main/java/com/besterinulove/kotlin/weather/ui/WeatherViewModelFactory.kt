package com.besterinulove.kotlin.weather.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.besterinulove.kotlin.weather.data.WeatherRepository

class WeatherViewModelFactory(
    private val weatherRepository:WeatherRepository
) :ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(weatherRepository) as T
        }
        throw IllegalArgumentException("Cant not create")
    }
}