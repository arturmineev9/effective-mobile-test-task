package ru.itis.effectivemobiletesttask.favorites.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.effectivemobiletesttask.favorites.api.usecase.GetFavoriteCoursesUseCase
import ru.itis.effectivemobiletesttask.favorites.impl.usecase.GetFavoriteCoursesUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
interface FavoriteModule {

    @Binds
    fun bindGetFavoriteCoursesUseCase(impl: GetFavoriteCoursesUseCaseImpl): GetFavoriteCoursesUseCase
}
