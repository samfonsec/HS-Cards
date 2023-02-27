package com.samfonsec.hscards.domain.useCase

import com.samfonsec.hscards.domain.model.Card
import com.samfonsec.hscards.domain.repository.CardRepository
import com.samfonsec.hscards.domain.response.DataResponse

class GetCardsUseCaseImpl(
    private val cardRepository: CardRepository
) : GetCardsUseCase {

    private val excludedTypes = listOf(HERO, HERO_POWER)

    override suspend fun execute(className: String): DataResponse<List<Card>> {
        val result = cardRepository.getCards(className)
        return if (result is DataResponse.Success)
            DataResponse.Success(result.data.filterNot { excludedTypes.contains(it.type) })
        else
            result
    }


    companion object {
        private const val HERO = "Hero"
        private const val HERO_POWER = "Hero Power"
    }
}
