package com.example.marvelapp.database.di

import com.example.marvelapp.database.MarvelDatabase
import com.example.watchedapp.database.dao.CharactersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesCharactersDao(
        database: MarvelDatabase,
    ): CharactersDao = database.charactersDao()
}