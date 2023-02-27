package com.samfonsec.hscards.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.samfonsec.hscards.domain.utils.Constants.CARDS_TABLE_NAME
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = CARDS_TABLE_NAME)
data class Card(
    @PrimaryKey
    val cardId: String,
    val name: String?,
    val text: String?,
    val type: String?,
    val img: String?,
    val flavor: String?,
    val faction: String?,
    val cardSet: String?,
    val rarity: String?,
    val playerClass: String?,
    val cost: Int?,
    val attack: Int?,
    val health: Int?,
    val durability: Int?
) : Parcelable