package com.besterinulove.kotlin.weather.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.besterinulove.kotlin.weather.db.entity.WeatherLocationEntity
import com.besterinulove.kotlin.weather.db.entity.WeatherRelation

@Dao
interface WeatherLocationDao : BaseDao<WeatherLocationEntity> {
    @Query("SELECT * FROM weatherLocations WHERE locationName=:locationName")
    fun getWeatherLocationLiveData(locationName: String): LiveData<WeatherRelation>

    @Query("SELECT * FROM weatherLocations")
    suspend fun getAll():List<WeatherLocationEntity>
}