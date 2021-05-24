package com.example.hearthstonedecks.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hearthstonedecks.DeckApplication
import com.example.hearthstonedecks.data.Card
import kotlinx.coroutines.runBlocking
import java.lang.IllegalArgumentException

class CardInfoDialogViewModel(slug: String) : ViewModel() {
    private val repository = DeckApplication.repository

    private val _card = MutableLiveData<Card>()
    val card: LiveData<Card>
        get() = _card

    init {
        runBlocking {
            _card.value = repository.getCard(slug)
        }
    }
}

class CardInfoDialogViewModelFactory(private val slug: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CardInfoDialogViewModel::class.java)) {
            return CardInfoDialogViewModel(slug) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}