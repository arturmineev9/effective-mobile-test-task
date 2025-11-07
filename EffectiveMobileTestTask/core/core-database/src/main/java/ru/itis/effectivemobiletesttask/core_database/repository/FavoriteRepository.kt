package ru.itis.effectivemobiletesttask.core_database.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.itis.effectivemobiletesttask.core_common.model.Course
import ru.itis.effectivemobiletesttask.core_database.dao.FavoriteCourseDao
import ru.itis.effectivemobiletesttask.core_database.mapper.toEntity
import ru.itis.effectivemobiletesttask.core_database.model.FavoriteCourseEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepository @Inject constructor(
    private val dao: FavoriteCourseDao
) {
    fun getAllFavorites(): Flow<List<FavoriteCourseEntity>> = dao.getAllFavorites()

    suspend fun isFavorite(courseId: Long): Boolean =
        dao.getFavoriteById(courseId) != null

    suspend fun toggleFavorite(course: Course) {
        if (isFavorite(course.id)) {
            dao.delete(course.toEntity())
        } else {
            dao.insert(course.toEntity())
        }
    }

    suspend fun insertFavorite(course: Course) {
        dao.insert(course.toEntity())
    }

    fun getFavoriteIds(): Flow<List<Long>> =
        dao.getAllFavorites().map { entities ->
            entities.map { it.id }
        }
}
