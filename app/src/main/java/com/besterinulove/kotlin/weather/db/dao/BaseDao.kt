package com.besterinulove.kotlin.weather.db.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy


interface BaseDao<in T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg obj: T)
}