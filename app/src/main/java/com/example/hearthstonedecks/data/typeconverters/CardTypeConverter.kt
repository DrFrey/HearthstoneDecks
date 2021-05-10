package com.example.hearthstonedecks.data.typeconverters

import androidx.room.TypeConverter
import com.example.hearthstonedecks.data.Card
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types


class CardTypeConverter {
    val moshi = Moshi.Builder().build()
    val type = Types.newParameterizedType(List::class.java, Card::class.java)
    val adapter = moshi.adapter<List<Card>>(type)

    @TypeConverter
    fun fromCardsToJson(cards : List<Card>) : String  {
        return adapter.toJson(cards)
    }

    @TypeConverter
    fun fromJsonToCards(json : String) : List<Card>? {
        return adapter.fromJson(json)
    }
}