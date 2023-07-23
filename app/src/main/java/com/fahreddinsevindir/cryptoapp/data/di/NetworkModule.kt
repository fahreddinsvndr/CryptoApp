package com.fahreddinsevindir.cryptoapp.data.di

import android.content.Context
import com.fahreddinsevindir.cryptoapp.BuildConfig
import com.fahreddinsevindir.cryptoapp.common.Constants.CONNECT_TIMEOUT
import com.fahreddinsevindir.cryptoapp.common.Constants.READ_TIMEOUT
import com.fahreddinsevindir.cryptoapp.data.intercepter.ApiKeyInterceptor
import com.fahreddinsevindir.cryptoapp.data.remote.CoinService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideHttpLoggerInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun provideHttpApiKeyInterceptor(): ApiKeyInterceptor {
        return ApiKeyInterceptor()
    }

    @Singleton
    @Provides
    fun provideHttpClint(
        httpLoggingInterceptor: HttpLoggingInterceptor, apiKeyInterceptor: ApiKeyInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS).addInterceptor(apiKeyInterceptor)
            .addInterceptor(httpLoggingInterceptor).build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(okHttpClient)
            .addConverterFactory(gsonConverterFactory).build()
    }

    @Singleton
    @Provides
    fun provideApiCoinService(retrofit: Retrofit): CoinService {
        return retrofit.create(CoinService::class.java)
    }



}