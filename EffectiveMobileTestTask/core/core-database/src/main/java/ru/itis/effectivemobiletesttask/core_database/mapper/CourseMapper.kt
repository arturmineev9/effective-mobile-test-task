package ru.itis.effectivemobiletesttask.core_database.mapper

import ru.itis.effectivemobiletesttask.core_common.model.Course
import ru.itis.effectivemobiletesttask.core_database.model.FavoriteCourseEntity


fun Course.toEntity() = FavoriteCourseEntity(
    id = id,
    title = title,
    text = text,
    price = price,
    rate = rate,
    startDate = startDate,
    publishDate = publishDate
)