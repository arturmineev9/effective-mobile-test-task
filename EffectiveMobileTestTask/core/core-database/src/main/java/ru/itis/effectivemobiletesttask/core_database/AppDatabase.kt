package ru.itis.effectivemobiletesttask.core_database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.itis.effectivemobiletesttask.core_database.dao.FavoriteCourseDao
import ru.itis.effectivemobiletesttask.core_database.model.FavoriteCourseEntity

@Database(
    entities = [FavoriteCourseEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteCourseDao(): FavoriteCourseDao
}
