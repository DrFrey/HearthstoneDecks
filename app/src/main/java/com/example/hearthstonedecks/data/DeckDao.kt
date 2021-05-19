package com.example.hearthstonedecks.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DeckDao {
    @Query("SELECT * FROM decks")
    fun getAllDecks() : Flow<List<DeckDBItem>>

    @Query("SELECT * FROM decks WHERE id = :deckId")
    suspend fun getDeckById(deckId : Int) : DeckDBItem

    @Query("DELETE FROM decks")
    suspend fun deleteAll()

    @Insert
    suspend fun insertDeck(deck: DeckDBItem)

    @Delete
    suspend fun deleteDeck(deck: DeckDBItem)
}