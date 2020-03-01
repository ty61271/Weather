package com.besterinulove.kotlin.weather.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.besterinulove.kotlin.weather.db.dao.WeatherElementDao
import com.besterinulove.kotlin.weather.db.dao.WeatherLocationDao
import com.besterinulove.kotlin.weather.db.entity.WeatherElementEntity
import com.besterinulove.kotlin.weather.db.entity.WeatherLocationEntity

@Database(
    entities = [
        WeatherLocationEntity::class,
        WeatherElementEntity::class
    ],
    version = 1
)
abstract class WeatherDatabase() : RoomDatabase() {
    abstract fun getWeatherLocationDao(): WeatherLocationDao
    abstract fun getWeatherElementDao(): WeatherElementDao

    companion object {
        private var instance: WeatherDatabase? = null
        fun getInstance(context: Context): WeatherDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(context: Context): WeatherDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                WeatherDatabase::class.java,
                "weather.db"
            ).build()
    }
}