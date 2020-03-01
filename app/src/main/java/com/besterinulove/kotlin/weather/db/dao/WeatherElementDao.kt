package com.besterinulove.kotlin.weather.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.besterinulove.kotlin.weather.db.entity.WeatherElementEntity

@Dao
interface WeatherElementDao : BaseDao<WeatherElementEntity> {
    @Query("SELECT * FROM weatherElements WHERE `index`=:index")
    suspend fun getElementWithIndex(index: Int):WeatherElementEntity
}