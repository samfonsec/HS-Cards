package com.samfonsec.hscards.domain.useCase

import com.samfonsec.hscards.domain.response.DataResponse

interface GetClassesUseCase {
    suspend fun execute(): DataResponse<List<String>>
}
