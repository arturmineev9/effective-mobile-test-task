package ru.itis.effectivemobiletesttask.feature_main.api.usecase

import kotlinx.coroutines.flow.Flow
import ru.itis.effectivemobiletesttask.core_common.model.Course

interface GetCoursesUseCase {
    suspend operator fun invoke(): Flow<List<Course>>
}
