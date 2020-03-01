package com.besterinulove.kotlin.weather.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class WeatherRelation(
    @Embedded
    var location: WeatherLocationEntity,
    @Relation(parentColumn = "locationName", entityColumn = "fkLocationName")
    var elements: List<WeatherElementEntity>
)