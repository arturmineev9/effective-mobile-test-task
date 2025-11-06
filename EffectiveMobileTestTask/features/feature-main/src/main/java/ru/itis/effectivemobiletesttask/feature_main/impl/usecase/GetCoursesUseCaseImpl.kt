package ru.itis.effectivemobiletesttask.feature_main.impl.usecase

import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import ru.itis.effectivemobiletesttask.core_common.model.Course
import ru.itis.effectivemobiletesttask.core_utils.IoDispatchers
import ru.itis.effectivemobiletesttask.feature_main.api.repository.CourseRepository
import ru.itis.effectivemobiletesttask.feature_main.api.usecase.GetCoursesUseCase


class GetCoursesUseCaseImpl @Inject constructor(
    @IoDispatchers private val coroutineDispatcher: CoroutineDispatcher,
    private val repository: CourseRepository
) : GetCoursesUseCase {
    override suspend operator fun invoke(): Flow<List<Course>> {
        return withContext(coroutineDispatcher)
        {
            repository.getAllCourses()
        }
    }
}