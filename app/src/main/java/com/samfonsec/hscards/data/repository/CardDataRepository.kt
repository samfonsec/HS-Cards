package com.samfonsec.hscards.data.repository

import com.samfonsec.hscards.data.dataSource.CardDataSource
import com.samfonsec.hscards.domain.model.Card
import com.samfonsec.hscards.domain.repository.CardRepository
import com.samfonsec.hscards.domain.response.DataResponse

class CardDataRepository(
    private val dataSource: CardDataSource
) : CardRepository {

    override suspend fun getCards(className: String): DataResponse<List<Card>> {
        return dataSource.getCards(className)
    }
}