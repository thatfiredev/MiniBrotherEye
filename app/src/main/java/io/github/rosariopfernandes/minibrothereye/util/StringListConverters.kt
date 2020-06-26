package io.github.rosariopfernandes.minibrothereye.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object StringListConverters {
    @TypeConverter
    @JvmStatic
    fun fromString(json: String): List<String> {
        return Gson().fromJson(json, object : TypeToken<List<String>>() {}.type)
    }

    @TypeConverter
    @JvmStatic
    fun fromStringList(list: List<String>) = Gson().toJson(list)
}