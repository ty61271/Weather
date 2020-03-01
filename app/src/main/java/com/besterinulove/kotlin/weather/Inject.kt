package com.besterinulove.kotlin.weather

import android.content.Context
import com.besterinulove.kotlin.weather.api.WeatherService
import com.besterinulove.kotlin.weather.data.DetailRepository
import com.besterinulove.kotlin.weather.data.WeatherRepository
import com.besterinulove.kotlin.weather.db.WeatherDatabase
import com.besterinulove.kotlin.weather.ui.DetailViewModelFactory
import com.besterinulove.kotlin.weather.ui.WeatherViewModelFactory

object Inject {
    fun provideWeatherViewModelFactory(context: Context) =
        WeatherViewModelFactory(provideWeatherRepository(context))

    private fun provideWeatherRepository(context: Context) =
        WeatherRepository(
            WeatherService.createService(),
            provideWeatherDatabase(context)
        )

    private fun provideWeatherDatabase(context: Context) = WeatherDatabase.getInstance(context)

    fun provideDetailViewModelFactory(context: Context) =
        DetailViewModelFactory(provideDetailRepository(context))

    private fun provideDetailRepository(context: Context) =
        DetailRepository(
            provideWeatherDatabase(context)
        )
}