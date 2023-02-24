package com.samfonsec.hscards.domain.repository

import com.samfonsec.hscards.domain.response.DataResponse

interface InfoRepository {
    suspend fun getClasses(): DataResponse<List<String>>
}