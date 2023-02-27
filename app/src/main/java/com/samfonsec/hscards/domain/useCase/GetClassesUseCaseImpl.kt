package com.samfonsec.hscards.domain.useCase

import com.samfonsec.hscards.domain.repository.InfoRepository
import com.samfonsec.hscards.domain.response.DataResponse

class GetClassesUseCaseImpl(
    private val infoRepository: InfoRepository
) : GetClassesUseCase {

    override suspend fun execute(): DataResponse<List<String>> {
        val result = infoRepository.getClasses()
        return if (result is DataResponse.Success)
            DataResponse.Success(result.data.filterNot { it == DREAM || it == WHIZBANG })
        else
            result
    }

    companion object {
        private const val DREAM = "Dream"
        private const val WHIZBANG = "Whizbang"
    }

}
