package com.example.hearthstonedecks.data.typeconverters

import androidx.room.TypeConverter
import com.example.hearthstonedecks.data.HeroPower
import com.squareup.moshi.Moshi

class HeroPowerTypeConverter {
    val moshi = Moshi.Builder().build()
    val adapter = moshi.adapter(HeroPower::class.java)

    @TypeConverter
    fun fromClassToJson(heroPower: HeroPower) : String  {
        return adapter.toJson(heroPower)
    }

    @TypeConverter
    fun fromJsonToClass(json : String) : HeroPower? {
        return adapter.fromJson(json)
    }
}