package com.example.hearthstonedecks.data.typeconverters

import androidx.room.TypeConverter
import com.example.hearthstonedecks.data.Class
import com.squareup.moshi.Moshi

class ClassTypeConverter {
    val moshi = Moshi.Builder().build()
    val adapter = moshi.adapter(Class::class.java)

    @TypeConverter
    fun fromClassToJson(clazz: Class) : String  {
        return adapter.toJson(clazz)
    }

    @TypeConverter
    fun fromJsonToClass(json : String) : Class? {
        return adapter.fromJson(json)
    }
}