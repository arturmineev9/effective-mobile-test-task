package ru.itis.effectivemobiletesttask.favorites.api.usecase

import kotlinx.coroutines.flow.Flow
import ru.itis.effectivemobiletesttask.core_common.model.Course

interface GetFavoriteCoursesUseCase {
    suspend operator fun invoke(): Flow<List<Course>>
}