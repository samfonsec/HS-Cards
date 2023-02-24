package com.samfonsec.hscards.data.dataSource

import com.samfonsec.hscards.data.api.InfoApi
import com.samfonsec.hscards.domain.response.DataResponse

class InfoDataSource(
    private val api: InfoApi
) {

    suspend fun getClasses(): DataResponse<List<String>> {
        return try {
            DataResponse.Success(api.getInfo().classes)
        } catch (e: Exception) {
            DataResponse.Error(e)
        }
    }
}
