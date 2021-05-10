package com.example.hearthstonedecks

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hearthstonedecks.data.typeconverters.CardTypeConverter
import com.example.hearthstonedecks.data.Deck
import com.example.hearthstonedecks.data.DeckDBItem
import com.example.hearthstonedecks.data.DeckDao
import com.example.hearthstonedecks.data.typeconverters.ClassTypeConverter
import com.example.hearthstonedecks.data.typeconverters.HeroPowerTypeConverter
import com.example.hearthstonedecks.data.typeconverters.HeroTypeConverter

@Database(entities = arrayOf(DeckDBItem::class), version = 3)
@TypeConverters(
    CardTypeConverter::class,
    ClassTypeConverter::class,
    HeroTypeConverter::class,
    HeroPowerTypeConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun deckDao(): DeckDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "deck_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}