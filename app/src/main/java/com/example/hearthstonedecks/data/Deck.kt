package com.example.hearthstonedecks.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Deck(

    @Json(name = "cardCount")
    val cardCount: Int?,

    @Json(name = "cards")
    val cards: List<Card>?,

    @Json(name = "class")
    val classX: Class?,

    @Json(name = "deckCode")
    val deckCode: String?,

    @Json(name = "format")
    val format: String?,

    @Json(name = "hero")
    val hero: Hero?,

    @Json(name = "heroPower")
    val heroPower: HeroPower?,

    @Json(name = "version")
    val version: Int?
)