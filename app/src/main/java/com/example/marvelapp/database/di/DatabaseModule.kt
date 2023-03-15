package com.example.watchedapp.database.di

import android.content.Context
import androidx.room.Room
import com.example.marvelapp.database.MarvelDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesCharactersDatabase(
        @ApplicationContext context: Context
    ): MarvelDatabase = Room.databaseBuilder(
        context,
        MarvelDatabase::class.java,
        "characters-database"
    ).build()
}