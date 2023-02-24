package com.samfonsec.hscards.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Card(
    val cardId: String,
    val name: String,
    val text: String,
    val type: String,
    val img: String?,
    val flavor: String?,
    val faction: String?,
    val cardSet: String?,
    val rarity: String?,
    val cost: Int?,
    val attack: Int?,
    val health: Int?,
    val durability: Int?
) : Parcelable