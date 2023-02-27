package com.samfonsec.hscards.data.repository

import com.samfonsec.hscards.data.dataSource.CardLocalDataSource
import com.samfonsec.hscards.data.dataSource.CardRemoteDataSource
import com.samfonsec.hscards.domain.model.Card
import com.samfonsec.hscards.domain.repository.CardRepository
import com.samfonsec.hscards.domain.response.DataResponse

class CardDataRepository(
    private val remoteDataSource: CardRemoteDataSource,
    private val localDataSource: CardLocalDataSource
) : CardRepository {

    override suspend fun getCards(className: String): DataResponse<List<Card>> {
        return localDataSource.getCards(className).takeIf {
            it is DataResponse.Success
        } ?: loadCards(className)
    }

    private suspend fun loadCards(className: String): DataResponse<List<Card>> {
        return remoteDataSource.getCards(className).also {
            if (it is DataResponse.Success) {
                saveCards(it.data)
            }
        }
    }

    private suspend fun saveCards(cards: List<Card>) {
        localDataSource.saveCards(cards)
    }
}