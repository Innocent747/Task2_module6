package com.example.task2module6.data.repository

import com.example.task2module6.data.remote.api.NobelApi
import com.example.task2module6.domain.model.Laureate
import com.example.task2module6.domain.model.NobelPrize
import com.example.task2module6.domain.repository.NobelRepository
import javax.inject.Inject

class NobelRepositoryImpl @Inject constructor(
    private val api: NobelApi
) : NobelRepository {

    override suspend fun getPrizes(
        limit: Int,
        offset: Int,
        year: Int?,
        category: String?
    ): List<NobelPrize> {
        val response = api.getNobelPrizes(limit, offset, year, category)
        return response.nobelPrizes.map { dto ->
            NobelPrize(
                awardYear = dto.awardYear,
                category = dto.category?.en ?: "",
                categoryFullName = dto.categoryFullName?.en ?: dto.category?.en ?: "",
                topMotivation = dto.topMotivation?.en ?: "",
                laureates = dto.laureates?.map { laureate ->
                    Laureate(
                        id = laureate.id,
                        fullName = laureate.fullName?.en ?: laureate.knownName?.en ?: "Организация",
                        knownName = laureate.knownName?.en ?: laureate.fullName?.en ?: "Организация",
                        portion = laureate.portion ?: "1",
                        motivation = laureate.motivation?.en ?: dto.topMotivation?.en ?: ""
                    )
                } ?: emptyList()
            )
        }
    }
}
