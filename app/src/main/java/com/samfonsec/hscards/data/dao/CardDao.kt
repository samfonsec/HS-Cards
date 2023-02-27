package com.samfonsec.hscards.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.samfonsec.hscards.domain.model.Card
import com.samfonsec.hscards.domain.utils.Constants.CARDS_TABLE_NAME

@Dao
interface CardDao {

    @Query("SELECT * FROM $CARDS_TABLE_NAME WHERE playerClass LIKE :className ORDER BY cost ASC")
    suspend fun getCards(className: String): List<Card>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCards(cards: List<Card>)
}
