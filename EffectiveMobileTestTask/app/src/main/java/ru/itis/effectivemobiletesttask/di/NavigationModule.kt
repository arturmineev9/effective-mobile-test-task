package ru.itis.effectivemobiletesttask.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.effectivemobiletesttask.core_navigation.Nav
import ru.itis.effectivemobiletesttask.core_navigation.NavLoginScreen
import ru.itis.effectivemobiletesttask.nav.NavImpl
import ru.itis.effectivemobiletesttask.nav.NavLoginScreenImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    @Singleton
    fun bindNavToImpl(impl: NavImpl): Nav

    @Binds
    @Singleton
    fun bindNavMainToImpl(impl: NavLoginScreenImpl): NavLoginScreen
}
