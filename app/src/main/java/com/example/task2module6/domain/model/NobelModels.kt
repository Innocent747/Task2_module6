package com.example.task2module6.domain.model

data class NobelPrize(
    val awardYear: String,
    val category: String,
    val categoryFullName: String,
    val topMotivation: String,
    val laureates: List<Laureate>
)

data class Laureate(
    val id: String,
    val fullName: String,
    val knownName: String,
    val portion: String,
    val motivation: String
)
