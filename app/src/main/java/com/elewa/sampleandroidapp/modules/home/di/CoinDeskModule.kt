package com.elewa.sampleandroidapp.modules.home.di

import androidx.paging.ExperimentalPagingApi
import com.elewa.sampleandroidapp.modules.home.data.repository.CoinDeskRepositoryImpl
import com.elewa.sampleandroidapp.modules.home.domain.repository.CoinDeskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@ExperimentalPagingApi
@InstallIn(ViewModelComponent::class)
@Module
abstract class CoinDeskModule {
    @Binds
    @ViewModelScoped
    abstract fun bindCoinDeskRepository(impl: CoinDeskRepositoryImpl): CoinDeskRepository
}