package com.samfonsec.hscards.domain.repository

import com.samfonsec.hscards.domain.model.Card
import com.samfonsec.hscards.domain.response.DataResponse

interface CardRepository {
    suspend fun getCards(className: String): DataResponse<List<Card>>
}