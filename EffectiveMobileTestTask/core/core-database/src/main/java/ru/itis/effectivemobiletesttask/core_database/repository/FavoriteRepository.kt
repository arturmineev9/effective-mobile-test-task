package ru.itis.effectivemobiletesttask.core_database.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
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
            Log.d("FavoriteDB", "Deleted: ${course.id}")
        } else {
            dao.insert(course.toEntity())
            Log.d("FavoriteDB", "Inserted: ${course.id}")
        }
    }
}
