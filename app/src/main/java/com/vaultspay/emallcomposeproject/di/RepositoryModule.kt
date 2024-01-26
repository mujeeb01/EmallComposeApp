package com.vaultspay.emallcomposeproject.di

import com.vaultspay.emallcomposeproject.data.repositoryImpl.auth.AuthRepositoryImpl
import com.vaultspay.emallcomposeproject.domain.repositories.auth.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun authRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository
}