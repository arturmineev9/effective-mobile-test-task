package ru.itis.effectivemobiletesttask.core_common.model

data class Course(
    val id: Long,
    val title: String,
    val text: String,
    val price: Int,
    val rate: Double,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)