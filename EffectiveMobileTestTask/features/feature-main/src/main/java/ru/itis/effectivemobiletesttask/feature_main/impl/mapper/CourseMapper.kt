package ru.itis.effectivemobiletesttask.feature_main.impl.mapper

import android.util.Log
import ru.itis.effectivemobiletesttask.core_common.model.Course
import ru.itis.effectivemobiletesttask.feature_main.impl.remote.model.CourseDto

object CourseMapper {
    fun map(dto: CourseDto): Course {

        val rate = dto.rateString.toDoubleOrNull() ?: 0.0

        return Course(
            id = dto.id,
            title = dto.title,
            text = dto.text,
            price = dto.priceString,
            rate = rate,
            startDate = dto.startDate,
            hasLike = dto.hasLike,
            publishDate = dto.publishDate
        )
    }

    fun mapList(dtos: List<CourseDto>): List<Course> = dtos.map(::map)
}
