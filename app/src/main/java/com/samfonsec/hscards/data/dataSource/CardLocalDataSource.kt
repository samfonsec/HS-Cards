package com.samfonsec.hscards.data.dataSource

import com.samfonsec.hscards.data.dao.CardDao
import com.samfonsec.hscards.domain.model.Card
import com.samfonsec.hscards.domain.response.DataResponse

class CardLocalDataSource(
    private val dao: CardDao
) {

    suspend fun getCards(className: String): DataResponse<List<Card>> {
        return try {
            dao.getCards(className).let {
                if (it.isNotEmpty())
                    DataResponse.Success(it)
                else
                    DataResponse.Error(Exception())
            }
        } catch (e: Exception) {
            DataResponse.Error(e)
        }
    }

    suspend fun saveCards(cards: List<Card>) {
        try {
            dao.saveCards(cards)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
