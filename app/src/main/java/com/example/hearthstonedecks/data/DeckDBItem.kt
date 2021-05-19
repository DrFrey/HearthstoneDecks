package com.example.hearthstonedecks.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "decks")
data class DeckDBItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "card_count")
    var cardCount: Int = 0,

    @ColumnInfo(name = "cards")
    var cards: List<Card> = listOf(),

    @ColumnInfo(name = "class")
    var classX: Class? = null,

    @ColumnInfo(name = "deck_code")
    var deckCode: String = "",

    @ColumnInfo(name = "format")
    var format: String = "",

    @ColumnInfo(name = "hero")
    var hero: Hero? = null,

    @ColumnInfo(name = "hero_power")
    var heroPower: HeroPower? = null,

    @ColumnInfo(name = "version")
    var version: Int = 1
)
