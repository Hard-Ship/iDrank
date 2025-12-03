package com.app.idrank.data

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "water_intake")
data class WaterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val amountMl: Int,

    val timestamp: Long
)
