package com.mfinatti.matheusmovies.core.room

import androidx.room.TypeConverter

/**
 * Room converter for string lists.
 */
class StringListConverter {

    /**
     * Converts from string to a string list.
     */
    @TypeConverter
    fun toStringList(string: String?): List<String> =
        string?.split(',') ?: listOf()

    /**
     * Converts from a string list to a string.
     */
    @TypeConverter
    fun listToString(list: List<String>): String = list.joinToString(",")
}
