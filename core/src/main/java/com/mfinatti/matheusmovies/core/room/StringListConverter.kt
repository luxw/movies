package com.mfinatti.matheusmovies.core.room

import androidx.room.TypeConverter

class StringListConverter {

    @TypeConverter
    fun toStringList(string: String?): List<String> =
        string?.split(',') ?: listOf()

    @TypeConverter
    fun listToString(list: List<String>): String = list.joinToString()
}