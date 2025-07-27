package com.example.superheroapi.di

import com.example.superheroapi.data.remote.ApiService
import com.example.superheroapi.domain.repository.SuperheroRepository
import com.example.superheroapi.domain.repository.SuperheroRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // GANTI DENGAN ACCESS TOKEN ANDA
    private const val YOUR_ACCESS_TOKEN = "22c03bc4f445a688894747669089c7cb"
    private const val BASE_URL = "https://superheroapi.com/api/$YOUR_ACCESS_TOKEN/"

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
    @Provides
    @Singleton
    fun provideSuperheroRepository(apiService: ApiService): SuperheroRepository {
        return SuperheroRepositoryImpl(apiService)
    }
}