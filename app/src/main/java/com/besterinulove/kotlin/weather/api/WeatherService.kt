package com.besterinulove.kotlin.weather.api

import com.besterinulove.kotlin.weather.model.WeatherResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherService {
    @Headers("Authorization:CWB-E80D8B08-C2AD-49B3-A908-B38328594BFA")

    @GET("v1/rest/datastore/F-C0032-001")
    suspend fun getWeather(
        @Query("limit") limit: Int = 1,
        @Query("offset") offset: Int = 0,
        @Query("format") format: String? = null,
        @Query("locationName") locationName: String? = null,
        @Query("elementName") elementName: String? = null,
        @Query("sort") sort: String? = null,
        @Query("startTime") startTime: List<String>? = emptyList(),
        @Query("timeFrom") timeFrom: String? = null,
        @Query("timeTo") timeTo: String? = null
    ): WeatherResponse

    companion object {
        const val BASE_URL = "https://opendata.cwb.gov.tw/api/"
        fun createService(): WeatherService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(WeatherService::class.java)
        }
    }
}