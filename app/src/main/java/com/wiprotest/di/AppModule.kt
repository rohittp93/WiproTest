package com.wiprotest.di

import com.wiprotest.data.RemoteRepositoryImpl
import  com.wiprotest.domain.repository.Repository
import com.wiprotest.domain.usecase.FetchUniversities
import com.wiprotest.domain.usecase.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@ExperimentalCoroutinesApi
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideRepository(): Repository = RemoteRepositoryImpl()

    @Provides
    fun provideUseCases(repository: Repository) = UseCases(
        fetchUniversities = FetchUniversities(repository)
    )
}