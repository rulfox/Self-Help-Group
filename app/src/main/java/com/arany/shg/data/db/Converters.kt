package com.arany.shg.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {

    @TypeConverter
    fun fromArrayListOfInt(source: ArrayList<Int>): String {
        return Gson().toJson(source)
    }

    @TypeConverter
    fun toSource(source: String): ArrayList<Int> {
        return Gson().fromJson(source, object : TypeToken<List<Int>>() {}.type)
    }
}
