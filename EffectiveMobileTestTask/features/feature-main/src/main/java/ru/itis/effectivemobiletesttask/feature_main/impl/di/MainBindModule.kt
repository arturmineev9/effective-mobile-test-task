package ru.itis.effectivemobiletesttask.feature_main.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.effectivemobiletesttask.feature_main.api.usecase.GetCoursesUseCase
import ru.itis.effectivemobiletesttask.feature_main.impl.usecase.GetCoursesUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
interface MainBindModule {

    @Binds
    fun bindGetGetCoursesToImpl(impl: GetCoursesUseCaseImpl): GetCoursesUseCase

}
