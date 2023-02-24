package com.samfonsec.hscards.data.api

import com.samfonsec.hscards.domain.model.Info
import retrofit2.http.GET

interface InfoApi {

    @GET(INFO_API)
    suspend fun getInfo(): Info

    companion object {
        private const val INFO_API = "info"
    }
}
