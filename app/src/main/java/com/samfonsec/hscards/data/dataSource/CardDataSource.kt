package com.samfonsec.hscards.data.dataSource

import com.samfonsec.hscards.data.api.CardApi
import com.samfonsec.hscards.domain.model.Card
import com.samfonsec.hscards.domain.response.DataResponse

class CardDataSource(
    private val api: CardApi
) {

    suspend fun getCards(className: String): DataResponse<List<Card>> {
        return try {
            DataResponse.Success(api.getCards(className).filter { it.type != "Hero" && it.type != "Hero Power" })
        } catch (e: Exception) {
            DataResponse.Error(e)
        }
    }
}
