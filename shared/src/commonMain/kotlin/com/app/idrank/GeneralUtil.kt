package com.app.idrank

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.format
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime

@OptIn(FormatStringsInDatetimeFormats::class)
fun Long.fromMillisToDate(pattern: String = "dd/MM/yyyy, HH:mm:ss"): String {
    val instant = Instant.fromEpochMilliseconds(this)
    // 1. Get LocalDateTime instead of stripping it down to LocalDate
    val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

    // 2. Use LocalDateTime.Format to support hours, minutes, seconds
    val formatter = LocalDateTime.Format {
        byUnicodePattern(pattern)
    }

    return localDateTime.format(formatter)
}

