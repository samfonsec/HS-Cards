package com.samfonsec.hscards.data.repository

import com.samfonsec.hscards.data.dataSource.InfoDataSource
import com.samfonsec.hscards.domain.repository.InfoRepository
import com.samfonsec.hscards.domain.response.DataResponse

class InfoDataRepository(
    private val dataSource: InfoDataSource
) : InfoRepository {

    override suspend fun getClasses(): DataResponse<List<String>> {
        return dataSource.getClasses()
    }
}