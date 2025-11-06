package ru.itis.effectivemobiletesttask.feature_main.impl.mapper

import android.util.Log
import ru.itis.effectivemobiletesttask.core_common.model.Course
import ru.itis.effectivemobiletesttask.feature_main.impl.remote.model.CourseDto

object CourseMapper {
    fun map(dto: CourseDto): Course {
        Log.d("CourseMapper", "DTO: id=${dto.id}, hasLike=${dto.hasLike}")
        val priceClean = dto.priceString.replace(" ", "")
        val price = priceClean.toIntOrNull() ?: 0

        val rate = dto.rateString.toDoubleOrNull() ?: 0.0

        return Course(
            id = dto.id,
            title = dto.title,
            text = dto.text,
            price = price,
            rate = rate,
            startDate = dto.startDate,
            hasLike = dto.hasLike,
            publishDate = dto.publishDate
        )
    }

    fun mapList(dtos: List<CourseDto>): List<Course> = dtos.map(::map)
}
