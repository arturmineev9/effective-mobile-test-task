package ru.itis.effectivemobiletesttask.feature_main.impl.ui.adapter

import ru.itis.effectivemobiletesttask.core_common.model.Course

data class CourseItemModel(
    val course: Course,
    val onFavoriteClick: (Course) -> Unit,
    val onDetailsClick: (Course) -> Unit
)
