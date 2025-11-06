package ru.itis.effectivemobiletesttask.feature_main.impl.repository


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import ru.itis.effectivemobiletesttask.core_common.model.Course
import ru.itis.effectivemobiletesttask.core_database.repository.FavoriteRepository
import ru.itis.effectivemobiletesttask.feature_main.api.repository.CourseRepository
import ru.itis.effectivemobiletesttask.feature_main.impl.mapper.CourseMapper
import ru.itis.effectivemobiletesttask.feature_main.impl.remote.datasource.CourseRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CourseRepositoryImpl @Inject constructor(
    private val remoteDataSource: CourseRemoteDataSource,
    private val favoriteRepository: FavoriteRepository
) : CourseRepository {

    private var initialCourses: List<Course>? = null

    override fun getAllCourses(): Flow<List<Course>> = flow {
        // 1. Загружаем из API один раз
        if (initialCourses == null) {
            val dtos = remoteDataSource.fetchCourses()
            val courses = CourseMapper.mapList(dtos)

            // 2. Синхронизируем hasLike = true → Room
            courses.filter { it.hasLike }.forEach { course ->
                if (!favoriteRepository.isFavorite(course.id)) {
                    favoriteRepository.insertFavorite(course)
                }
            }

            initialCourses = courses
        }

        emit(initialCourses!!)
    }.flatMapLatest { courses ->
        // 3. Слушаем изменения в избранном и обновляем hasLike
        favoriteRepository.getFavoriteIds()
            .map { favoriteIds ->
                courses.map { it.copy(hasLike = it.id in favoriteIds) }
            }
    }
}
