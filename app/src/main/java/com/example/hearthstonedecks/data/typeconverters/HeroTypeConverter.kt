package com.example.hearthstonedecks.data.typeconverters

import androidx.room.TypeConverter
import com.example.hearthstonedecks.data.Hero
import com.squareup.moshi.Moshi

class HeroTypeConverter {
    val moshi = Moshi.Builder().build()
    val adapter = moshi.adapter(Hero::class.java)

    @TypeConverter
    fun fromClassToJson(hero: Hero) : String  {
        return adapter.toJson(hero)
    }

    @TypeConverter
    fun fromJsonToClass(json : String) : Hero? {
        return adapter.fromJson(json)
    }
}