package com.example.task2module6.domain.usecase

import com.example.task2module6.domain.model.NobelPrize
import com.example.task2module6.domain.repository.NobelRepository
import javax.inject.Inject

class GetPrizesUseCase @Inject constructor(
    private val repository: NobelRepository
) {
    suspend operator fun invoke(
        limit: Int = 25,
        offset: Int = 0,
        year: Int? = null,
        category: String? = null
    ): List<NobelPrize> = repository.getPrizes(limit, offset, year, category)
}
