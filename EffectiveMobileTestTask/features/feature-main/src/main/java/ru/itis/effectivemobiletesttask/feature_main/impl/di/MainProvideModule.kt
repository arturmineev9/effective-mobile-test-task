package ru.itis.effectivemobiletesttask.feature_main.impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import retrofit2.Retrofit
import ru.itis.effectivemobiletesttask.feature_main.impl.remote.api.CourseApiService

@Module
@InstallIn(SingletonComponent::class)
object MainProvideModule {

    @Provides
    fun provideCourseApiService(retrofit: Retrofit): CourseApiService =
        retrofit.create(CourseApiService::class.java)
}