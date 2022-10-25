package com.arany.shg.data.db

import androidx.room.TypeConverter
import com.arany.shg.feature_attendance.data.model.AttendanceStatus
import com.arany.shg.feature_thrift.data.model.ThriftStatus
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

    @TypeConverter
    fun toAttendanceStatus(value: String) = enumValueOf<AttendanceStatus>(value)

    @TypeConverter
    fun fromAttendanceStatus(value: AttendanceStatus) = value.name

    @TypeConverter
    fun toThriftStatus(value: String) = enumValueOf<ThriftStatus>(value)

    @TypeConverter
    fun fromThriftStatus(value: ThriftStatus) = value.name
}