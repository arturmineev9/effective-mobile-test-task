package ru.itis.effectivemobiletesttask.feature_main.impl.usecase


import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.itis.effectivemobiletesttask.core_common.model.Course
import ru.itis.effectivemobiletesttask.core_database.repository.FavoriteRepository
import ru.itis.effectivemobiletesttask.core_utils.IoDispatchers
import ru.itis.effectivemobiletesttask.feature_main.api.usecase.ToggleFavoriteUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ToggleFavoriteUseCaseImpl @Inject constructor(
    @IoDispatchers private val coroutineDispatcher: CoroutineDispatcher,
    private val favoriteRepository: FavoriteRepository
) : ToggleFavoriteUseCase {
    override suspend fun invoke(course: Course) {
        withContext(coroutineDispatcher) {
            favoriteRepository.toggleFavorite(course)
        }
    }
}
