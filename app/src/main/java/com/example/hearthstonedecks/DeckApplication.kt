package com.example.hearthstonedecks

import android.app.Application

class DeckApplication : Application() {

    companion object {
        private lateinit var instance : DeckApplication
        val database by lazy { AppDatabase.getDatabase(instance) }
        val repository by lazy { DeckRepository(database.deckDao()) }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}