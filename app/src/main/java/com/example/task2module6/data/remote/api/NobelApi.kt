package com.example.task2module6.data.remote.api

import com.example.task2module6.data.remote.dto.NobelPrizesResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class NobelApi @Inject constructor(private val client: HttpClient) {

    suspend fun getNobelPrizes(
        limit: Int = 25,
        offset: Int = 0,
        year: Int? = null,
        category: String? = null
    ): NobelPrizesResponseDto {
        return client.get("https://api.nobelprize.org/2.1/nobelPrizes") {
            parameter("limit", limit)
            parameter("offset", offset)
            year?.let { parameter("nobelPrizeYear", it) }
            category?.let { parameter("nobelPrizeCategory", it) }
        }.body()
    }
}
