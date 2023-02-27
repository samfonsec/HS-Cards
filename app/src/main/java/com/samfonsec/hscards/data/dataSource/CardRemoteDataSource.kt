package com.samfonsec.hscards.data.dataSource

import com.samfonsec.hscards.data.api.CardApi
import com.samfonsec.hscards.domain.model.Card
import com.samfonsec.hscards.domain.response.DataResponse

class CardRemoteDataSource(
    private val api: CardApi
) {

    suspend fun getCards(className: String): DataResponse<List<Card>> {
        return try {
            DataResponse.Success(
                api.getCards(className))
        } catch (e: Exception) {
            DataResponse.Error(e)
        }
    }
}
