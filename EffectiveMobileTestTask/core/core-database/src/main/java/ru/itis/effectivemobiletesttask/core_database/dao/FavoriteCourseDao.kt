package ru.itis.effectivemobiletesttask.core_database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.itis.effectivemobiletesttask.core_database.model.FavoriteCourseEntity

@Dao
interface FavoriteCourseDao {
    @Query("SELECT * FROM favorite_courses")
    fun getAllFavorites(): Flow<List<FavoriteCourseEntity>>

    @Query("SELECT * FROM favorite_courses WHERE id = :courseId")
    suspend fun getFavoriteById(courseId: Long): FavoriteCourseEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(course: FavoriteCourseEntity)

    @Delete
    suspend fun delete(course: FavoriteCourseEntity)
}