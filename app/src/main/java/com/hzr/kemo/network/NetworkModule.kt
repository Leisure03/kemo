package com.hzr.kemo.network

import com.hzr.kemo.api.DrinkListApiService
import com.hzr.kemo.repository.DrinkListRepository
import com.hzr.kemo.repository.irepo.IDrinkListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideHttpClient(): HttpClient {
        return KtorClient.client
    }
    @Provides
    fun provideDrinkListApiService(client: HttpClient): DrinkListApiService {
        return DrinkListApiService(client)
    }

    @Provides
    fun provideDrinkListRepository(apiService: DrinkListApiService): IDrinkListRepository {
        return DrinkListRepository(apiService)
    }
}