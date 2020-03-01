package com.besterinulove.kotlin.weather.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.besterinulove.kotlin.weather.model.Time

@Entity(
    tableName = "weatherElements",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = WeatherLocationEntity::class,
            parentColumns = arrayOf("locationName"),
            childColumns = arrayOf("fkLocationName"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class WeatherElementEntity(
    @PrimaryKey(autoGenerate = true)
    var index: Int = 0,
    var elementName: String = "",
    var startTime: String = "",
    var endTime: String = "",
    var parameterName: String = "",
    var parameterUnit: String = "",
    var fkLocationName: String = ""
) {
    companion object{
        fun instance(locationName: String, elementName: String, time: Time) = WeatherElementEntity(
            elementName = elementName,
            startTime = time.startTime,
            endTime = time.endTime,
            parameterName = time.parameter.parameterName,
            parameterUnit = time.parameter.parameterUnit,
            fkLocationName = locationName
        )
    }
}