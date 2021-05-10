package com.example.hearthstonedecks

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.hearthstonedecks.data.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import java.lang.Exception
import java.net.UnknownHostException
import java.time.LocalDateTime
import java.time.ZoneId

class DeckRepository(private val deckDao: DeckDao) {

    val allDecks: Flow<List<DeckDBItem>> = deckDao.getAllDecks()

    val errorReceived = MutableLiveData<Boolean>()
    var errorMessage = MutableLiveData<String>()

    private var accessData: AccessData? = null
    private var accessToken = ""
    private var tokenExpiry: Long = -1
    private var accessGranted: Boolean = false


    init {
        runBlocking {
            getAccess()
        }
    }

    // функции для работы с Room
    suspend fun insert(deck: DeckDBItem) {
        deckDao.insertDeck(deck)
    }


    // функции для получения данных от Blizzard

    // получить токен для доступа
    private suspend fun getAccess() {
        if (isTokenExpired()) {
            accessGranted = false
            try {
                accessData = BlizzardAuth.retrofitAuthService.getToken()
                accessData?.let {
                    accessToken = it.accessToken
                    tokenExpiry = LocalDateTime.now().atZone(ZoneId.systemDefault())
                        .toEpochSecond() + it.expiresIn
                }
                Log.d("___", "data received: ${accessData}")
                errorReceived.postValue(false)
                accessGranted = true
            } catch (e: UnknownHostException) {
                errorMessage.postValue("Check your internet connection and try again")
                errorReceived.postValue(true)
            } catch (e: Exception) {
                errorMessage.postValue(e.message.toString())
                errorReceived.postValue(true)
                Log.d("___", "error received: ${e.message.toString()}")
                Log.d("___", "error received: ${e}")
            }
        }
    }


    private fun isTokenExpired() =
        LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond() > tokenExpiry

    // получить список карт
    suspend fun getCards() {
        getAccess()
        if (accessGranted) {
            try {
                Log.d("___", "receiving cards")
                val response = BlizzardApi.retrofitService.getCards(accessToken = accessToken)
                Log.d("___", response.toString())
            } catch (e: UnknownHostException) {
                errorMessage.postValue("Check your internet connection and try again")
                errorReceived.postValue(true)
            } catch (e: Exception) {
                errorMessage.postValue(e.message.toString())
                errorReceived.postValue(true)
                Log.d("___", "error received: ${e.message.toString()}")
            }
        }
    }


    // получить карту по слагу
    suspend fun getCard(slug: String): Card? {
        getAccess()
        if (accessGranted) {
            try {
                Log.d("___", "receiving card")
                val card =
                    BlizzardApi.retrofitService.getCard(slug = slug, accessToken = accessToken)
                Log.d("___", card.toString())
                return card
            } catch (e: UnknownHostException) {
                errorMessage.postValue("Check your internet connection and try again")
                errorReceived.postValue(true)
            } catch (e: Exception) {
                errorMessage.postValue(e.message.toString())
                errorReceived.postValue(true)
                Log.d("___", "error received: ${e.message.toString()}")
            }
        }
        return null
    }

    // получить колоду по коду
    suspend fun getDeck(code: String): Deck? {
        getAccess()
        if (accessGranted) {
            try {
                Log.d("___", "receiving deck")
                val deck =
                    BlizzardApi.retrofitService.getDeck(accessToken = accessToken, code = code)
                Log.d("___", deck.toString())
                return deck
            } catch (e: UnknownHostException) {
                errorMessage.postValue("Check your internet connection and try again")
                errorReceived.postValue(true)
            } catch (e: Exception) {
                errorMessage.postValue(e.message.toString())
                errorReceived.postValue(true)
                Log.d("___", "error received: ${e.message.toString()}")
            }
        }
        return null
    }
}