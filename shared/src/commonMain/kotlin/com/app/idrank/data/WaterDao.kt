package com.app.idrank.data

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WaterDao {

    @Insert
    suspend fun insert(water: WaterEntity)

    @Query("""
        SELECT * FROM water_intake
        WHERE timestamp >= :startOfDay
        AND timestamp < :endOfDay
        ORDER BY timestamp DESC
    """)
    fun getTodayWater(
        startOfDay: Long,
        endOfDay: Long
    ): Flow<List<WaterEntity>>

    @Query("DELETE FROM water_intake WHERE id = :id")
    suspend fun delete(id: Long)
}