package ru.itis.effectivemobiletesttask.core_database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.itis.effectivemobiletesttask.core_database.AppDatabase
import ru.itis.effectivemobiletesttask.core_database.dao.FavoriteCourseDao
import ru.itis.effectivemobiletesttask.core_database.repository.FavoriteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "courses.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(database: AppDatabase): FavoriteCourseDao =
        database.favoriteCourseDao()

    @Provides
    @Singleton
    fun provideFavoriteRepository(dao: FavoriteCourseDao): FavoriteRepository =
        FavoriteRepository(dao)
}
