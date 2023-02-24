package com.samfonsec.hscards.data.api

import com.samfonsec.hscards.domain.model.Card
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CardApi {

    @GET(CARDS_BY_CLASS_API)
    suspend fun getCards(
        @Path("name") className: String,
        @Query("collectible") collectible: Int = 1
    ): List<Card>

    companion object {
        private const val CARDS_BY_CLASS_API = "cards/classes/{name}"
    }

}
