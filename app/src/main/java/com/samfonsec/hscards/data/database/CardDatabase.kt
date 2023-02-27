package com.samfonsec.hscards.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.samfonsec.hscards.data.dao.CardDao
import com.samfonsec.hscards.domain.model.Card

@Database(
    entities = [Card::class],
    version = 1
)
@TypeConverters(CardTypeConverter::class)
abstract class CardDatabase : RoomDatabase() {

    abstract fun cardDao(): CardDao

    companion object {
        private const val DB_FILE_NAME = "card_db"

        @Volatile
        private var instance: CardDatabase? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context): CardDatabase {
            return Room.databaseBuilder(context, CardDatabase::class.java, DB_FILE_NAME).build()
        }
    }
}