package ru.itis.effectivemobiletesttask.core_utils.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.itis.effectivemobiletesttask.core_utils.IoDispatchers

@Module
@InstallIn(SingletonComponent::class)
class ProvideModule {

    @Provides
    @IoDispatchers
    fun provideIoDispatchers(): CoroutineDispatcher = Dispatchers.IO
}
