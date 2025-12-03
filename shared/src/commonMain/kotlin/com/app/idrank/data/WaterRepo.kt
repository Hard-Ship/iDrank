package com.app.idrank.data

import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

class WaterRepo(
    private val waterDao: WaterDao
) {

    fun getTodayWater(): Flow<List<WaterEntity>> {
        val timeZone = TimeZone.currentSystemDefault()

        val date = Clock.System
            .now()
            .toLocalDateTime(timeZone)
            .date

        val startOfDay = date
            .atStartOfDayIn(timeZone)
            .toEpochMilliseconds()

        val endOfDay = date
            .plus(DatePeriod(days = 1))
            .atStartOfDayIn(timeZone)
            .toEpochMilliseconds()

        return waterDao.getTodayWater(
            startOfDay = startOfDay,
            endOfDay = endOfDay
        )
    }

    suspend fun addWater(amountMl: Int) {
        waterDao.insert(
            WaterEntity(
                amountMl = amountMl,
                timestamp = Clock.System.now().toEpochMilliseconds()
            )
        )
    }

    suspend fun deleteWater(id: Long) {
        waterDao.delete(id)
    }
}
