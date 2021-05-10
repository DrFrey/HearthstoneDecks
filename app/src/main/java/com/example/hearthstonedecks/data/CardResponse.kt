package com.example.hearthstonedecks.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CardResponse(
    @Json(name = "cardCount")
    val cardCount: Int,
    @Json(name = "cards")
    val cards: List<Card>,
    @Json(name = "page")
    val page: Int,
    @Json(name = "pageCount")
    val pageCount: Int
)