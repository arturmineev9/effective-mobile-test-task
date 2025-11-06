package ru.itis.effectivemobiletesttask.feature_main.impl.remote.model

import com.google.gson.annotations.SerializedName

data class CourseDto(
    val id: Long,
    val title: String,
    val text: String,
    @SerializedName("price")
    val priceString: String,
    @SerializedName("rate")
    val rateString: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)