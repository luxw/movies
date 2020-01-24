package com.mfinatti.matheusmovies.core.room

import androidx.room.TypeConverter
import java.util.Date

/**
 * Room converter for [Date].
 */
class DateConverter {

    /**
     * Converts a timestamp into a [Date].
     */
    @TypeConverter
    fun toDate(timestamp: Long?) = if (timestamp == null) null else Date(timestamp)

    /**
     * Converts a [Date] into a timestamp, which is [Long].
     */
    @TypeConverter
    fun toTimestamp(date: Date?) = date?.time
}
