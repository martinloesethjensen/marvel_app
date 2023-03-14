package com.example.marvelapp.data.di

import com.example.marvelapp.data.repositories.character.CharacterRepository
import com.example.marvelapp.data.repositories.character.RemoteCharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsCharacterRepository(
        characterRepository: RemoteCharacterRepository,
    ): CharacterRepository
}