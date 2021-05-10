package com.example.hearthstonedecks.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Duels(
    @Json(name = "constructed")
    val constructed: Boolean?,
    @Json(name = "relevant")
    val relevant: Boolean?
)