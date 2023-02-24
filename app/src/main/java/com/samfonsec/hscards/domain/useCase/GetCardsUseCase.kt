package com.samfonsec.hscards.domain.useCase

import com.samfonsec.hscards.domain.model.Card
import com.samfonsec.hscards.domain.response.DataResponse

interface GetCardsUseCase {
    suspend fun execute(className: String): DataResponse<List<Card>>
}
