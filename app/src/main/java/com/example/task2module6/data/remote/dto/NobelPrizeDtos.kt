package com.example.task2module6.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NobelPrizesResponseDto(
    @SerialName("nobelPrizes") val nobelPrizes: List<NobelPrizeDto> = emptyList()
)

@Serializable
data class NobelPrizeDto(
    @SerialName("awardYear") val awardYear: String = "",
    @SerialName("category") val category: CategoryDto? = null,
    @SerialName("categoryFullName") val categoryFullName: LocalizedDto? = null,
    @SerialName("dateAwarded") val dateAwarded: String? = null,
    @SerialName("prizeAmount") val prizeAmount: Long? = null,
    @SerialName("topMotivation") val topMotivation: LocalizedDto? = null,
    @SerialName("laureates") val laureates: List<LaureateDto>? = emptyList()
)

@Serializable
data class CategoryDto(
    @SerialName("en") val en: String = "",
    @SerialName("no") val no: String? = null,
    @SerialName("se") val se: String? = null
)

@Serializable
data class LocalizedDto(
    @SerialName("en") val en: String? = null,
    @SerialName("no") val no: String? = null,
    @SerialName("se") val se: String? = null
)

@Serializable
data class LaureateDto(
    @SerialName("id") val id: String = "",
    @SerialName("knownName") val knownName: LocalizedDto? = null,
    @SerialName("fullName") val fullName: LocalizedDto? = null,
    @SerialName("portion") val portion: String? = null,
    @SerialName("sortOrder") val sortOrder: String? = null,
    @SerialName("motivation") val motivation: LocalizedDto? = null,
    @SerialName("links") val links: List<LinkDto>? = null
)

@Serializable
data class LinkDto(
    @SerialName("rel") val rel: String? = null,
    @SerialName("href") val href: String? = null,
    @SerialName("action") val action: String? = null,
    @SerialName("types") val types: String? = null
)
