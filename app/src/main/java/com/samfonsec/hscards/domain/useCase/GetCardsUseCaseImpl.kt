package com.samfonsec.hscards.domain.useCase

import com.samfonsec.hscards.domain.model.Card
import com.samfonsec.hscards.domain.repository.CardRepository
import com.samfonsec.hscards.domain.response.DataResponse

class GetCardsUseCaseImpl(
    private val cardRepository: CardRepository
) : GetCardsUseCase {

    override suspend fun execute(className: String): DataResponse<List<Card>> {
        return cardRepository.getCards(className)
    }
}
