package ru.itis.effectivemobiletesttask.feature_main.api.repository

import kotlinx.coroutines.flow.Flow
import ru.itis.effectivemobiletesttask.core_common.model.Course

interface CourseRepository {
    fun getAllCourses(): Flow<List<Course>>
}
