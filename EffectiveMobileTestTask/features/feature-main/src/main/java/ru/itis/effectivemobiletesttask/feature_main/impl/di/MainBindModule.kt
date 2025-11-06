package ru.itis.effectivemobiletesttask.feature_main.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.effectivemobiletesttask.feature_main.api.repository.CourseRepository
import ru.itis.effectivemobiletesttask.feature_main.api.usecase.GetCoursesUseCase
import ru.itis.effectivemobiletesttask.feature_main.api.usecase.ToggleFavoriteUseCase
import ru.itis.effectivemobiletesttask.feature_main.impl.remote.datasource.CourseRemoteDataSource
import ru.itis.effectivemobiletesttask.feature_main.impl.remote.datasource.CourseRemoteDataSourceImpl
import ru.itis.effectivemobiletesttask.feature_main.impl.repository.CourseRepositoryImpl
import ru.itis.effectivemobiletesttask.feature_main.impl.usecase.GetCoursesUseCaseImpl
import ru.itis.effectivemobiletesttask.feature_main.impl.usecase.ToggleFavoriteUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
interface MainBindModule {

    @Binds
    fun bindGetCoursesToImpl(impl: GetCoursesUseCaseImpl): GetCoursesUseCase

    @Binds
    fun bindToggleFavoriteUseCase(impl: ToggleFavoriteUseCaseImpl): ToggleFavoriteUseCase

    @Binds
    fun bindCourseRepository(impl: CourseRepositoryImpl): CourseRepository

    @Binds
    fun bindCourseRemoteDataSource(impl: CourseRemoteDataSourceImpl): CourseRemoteDataSource
}
