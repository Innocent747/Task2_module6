package com.example.task2module6.domain.repository

import com.example.task2module6.domain.model.NobelPrize

interface NobelRepository {
    suspend fun getPrizes(
        limit: Int = 25,
        offset: Int = 0,
        year: Int? = null,
        category: String? = null
    ): List<NobelPrize>
}
