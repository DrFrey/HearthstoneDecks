package com.example.hearthstonedecks.ui

import android.util.Log
import androidx.lifecycle.*
import com.example.hearthstonedecks.DeckRepository
import com.example.hearthstonedecks.data.Deck
import com.example.hearthstonedecks.data.DeckDBItem
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException


class DeckListViewModel(private val repository: DeckRepository) : ViewModel() {

    val allDecks: LiveData<List<DeckDBItem>> = repository.allDecks.asLiveData()

    val errorReceived: LiveData<Boolean> = repository.errorReceived

    val errorMessage: LiveData<String> = repository.errorMessage

    fun importDeck(code: String) {
        viewModelScope.launch {
            val deck = repository.getDeck(code)
            if (deck != null) {
                repository.insert(convert(deck))
                Log.d("___", "Importing deck. ${deck.hero}")
                Log.d("___", "${allDecks.value?.size}")
            } else {
                Log.d("___", "Cannot import deck. Deck is null.")
            }
        }
    }

    private fun convert(deck: Deck): DeckDBItem = DeckDBItem(
        id = 0,
        cardCount = deck.cardCount!!,
        cards = deck.cards!!,
        classX = deck.classX,
        deckCode = deck.deckCode!!,
        format = deck.format!!,
        hero = deck.hero,
        heroPower = deck.heroPower,
        version = deck.version!!,
        name = "${deck.format} ${deck.classX?.name}"
    )

    fun deleteDeck(deck: DeckDBItem) {
        viewModelScope.launch {
            repository.delete(deck)
        }
    }
}

class DeckListViewModelFactory(private val repository: DeckRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeckListViewModel::class.java)) {
            return DeckListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}