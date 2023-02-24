package com.samfonsec.hscards.domain.useCase

import com.samfonsec.hscards.domain.repository.InfoRepository
import com.samfonsec.hscards.domain.response.DataResponse

class GetClassesUseCaseImpl(
    private val infoRepository: InfoRepository
) : GetClassesUseCase {

    override suspend fun execute(): DataResponse<List<String>> {
        return infoRepository.getClasses()
    }
}
