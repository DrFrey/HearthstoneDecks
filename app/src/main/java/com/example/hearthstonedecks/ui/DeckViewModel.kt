package com.example.hearthstonedecks.ui

import android.util.Log
import androidx.lifecycle.*
import com.example.hearthstonedecks.DeckRepository
import com.example.hearthstonedecks.data.DeckDBItem
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class DeckViewModel(private val deckId: Int, private val repository: DeckRepository) : ViewModel() {

    private val _deck = MutableLiveData<DeckDBItem>()
    val deck: LiveData<DeckDBItem>
        get() = _deck

    init {
        viewModelScope.launch {
            Log.d("___","in viewmodel init. deckId = $deckId")
            _deck.value = repository.getDeckById(deckId)
        }
    }


}

class DeckViewModelFactory(private var deckId: Int, private var repository: DeckRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeckViewModel::class.java)) {
            return DeckViewModel(deckId, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}