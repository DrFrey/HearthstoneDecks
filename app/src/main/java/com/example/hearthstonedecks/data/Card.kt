package com.example.hearthstonedecks.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Card(
    @Json(name = "armor")
    val armor: Int?,
    @Json(name = "artistName")
    val artistName: String?,
    @Json(name = "attack")
    val attack: Int?,
    @Json(name = "cardSetId")
    val cardSetId: Int?,
    @Json(name = "cardTypeId")
    val cardTypeId: Int?,
    @Json(name = "childIds")
    val childIds: List<Int>?,
    @Json(name = "classId")
    val classId: Int?,
    @Json(name = "collectible")
    val collectible: Int?,
    @Json(name = "cropImage")
    val cropImage: String?,
    @Json(name = "duels")
    val duels: Duels?,
    @Json(name = "durability")
    val durability: Int?,
    @Json(name = "flavorText")
    val flavorText: String?,
    @Json(name = "health")
    val health: Int?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "image")
    val image: String?,
    @Json(name = "imageGold")
    val imageGold: String?,
    @Json(name = "keywordIds")
    val keywordIds: List<Int>?,
    @Json(name = "manaCost")
    val manaCost: Int?,
    @Json(name = "minionTypeId")
    val minionTypeId: Int?,
    @Json(name = "multiClassIds")
    val multiClassIds: List<Any>?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "rarityId")
    val rarityId: Int?,
    @Json(name = "slug")
    val slug: String?,
    @Json(name = "spellSchoolId")
    val spellSchoolId: Int?,
    @Json(name = "text")
    val text: String?
)