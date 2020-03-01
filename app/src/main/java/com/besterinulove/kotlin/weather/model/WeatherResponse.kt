package com.besterinulove.kotlin.weather.model
import com.google.gson.annotations.SerializedName


data class WeatherResponse(
    @SerializedName("records")
    var records: Records = Records(),
    @SerializedName("result")
    var result: Result = Result(),
    @SerializedName("success")
    var success: String = ""
)

data class Records(
    @SerializedName("datasetDescription")
    var datasetDescription: String = "",
    @SerializedName("location")
    var location: List<Location> = listOf()
)

data class Location(
    @SerializedName("locationName")
    var locationName: String = "",
    @SerializedName("weatherElement")
    var weatherElement: List<WeatherElement> = listOf()
)

data class WeatherElement(
    @SerializedName("elementName")
    var elementName: String = "",
    @SerializedName("time")
    var time: List<Time> = listOf()
)

data class Time(
    @SerializedName("endTime")
    var endTime: String = "",
    @SerializedName("parameter")
    var parameter: Parameter = Parameter(),
    @SerializedName("startTime")
    var startTime: String = ""
)

data class Parameter(
    @SerializedName("parameterName")
    var parameterName: String = "",
    @SerializedName("parameterUnit")
    var parameterUnit: String = ""
)

data class Result(
    @SerializedName("fields")
    var fields: List<Field> = listOf(),
    @SerializedName("resource_id")
    var resourceId: String = ""
)

data class Field(
    @SerializedName("id")
    var id: String = "",
    @SerializedName("type")
    var type: String = ""
)

enum class Locations(val locationName: String){
    TaiPei("臺北市")
}
enum class WeatherElements(val weatherElement: String){
    MinT("MinT")
}