package com.fahreddinsevindir.cryptoapp.data.di

import com.fahreddinsevindir.cryptoapp.data.remote.CoinService
import com.fahreddinsevindir.cryptoapp.data.repository.CoinRepositoryImpl
import com.fahreddinsevindir.cryptoapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCoinRepository(coinService: CoinService): CoinRepository {
        return CoinRepositoryImpl(coinService)
    }
}