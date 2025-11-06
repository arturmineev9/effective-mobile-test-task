package ru.itis.effectivemobiletesttask.favorites.impl.usecase

import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.itis.effectivemobiletesttask.core_common.model.Course
import ru.itis.effectivemobiletesttask.core_database.mapper.toCourse
import ru.itis.effectivemobiletesttask.core_database.repository.FavoriteRepository
import ru.itis.effectivemobiletesttask.core_utils.IoDispatchers
import ru.itis.effectivemobiletesttask.favorites.api.usecase.GetFavoriteCoursesUseCase

class GetFavoriteCoursesUseCaseImpl @Inject constructor(
    @IoDispatchers private val coroutineDispatcher: CoroutineDispatcher,
    private val favoriteRepository: FavoriteRepository
) : GetFavoriteCoursesUseCase {
    override suspend operator fun invoke(): Flow<List<Course>> {
        return withContext(coroutineDispatcher) {
            favoriteRepository.getAllFavorites()
                .map { entities -> entities.map { it.toCourse() } }
        }
    }
}
