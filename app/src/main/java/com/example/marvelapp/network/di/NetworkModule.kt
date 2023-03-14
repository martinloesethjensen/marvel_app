package com.example.marvelapp.network.di

import com.example.marvelapp.network.CharacterNetworkDataSource
import com.example.marvelapp.network.retrofit.RetrofitCharacterNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun okHttpCallFactory(): Call.Factory = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun providesRetrofitCharacterNetwork(): CharacterNetworkDataSource =
        RetrofitCharacterNetwork(
            networkJson = providesNetworkJson(),
            okHttpCallFactory = okHttpCallFactory()
        )
}