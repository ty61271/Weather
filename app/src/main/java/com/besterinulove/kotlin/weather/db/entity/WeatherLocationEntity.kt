package com.besterinulove.kotlin.weather.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.besterinulove.kotlin.weather.model.Location

@Entity(tableName = "weatherLocations")
data class WeatherLocationEntity(
    @PrimaryKey
    var locationName: String = ""
) {
    companion object{
        fun instance(location: Location) =
            WeatherLocationEntity(
                locationName = location.locationName
            )
    }
}