package com.example.marvelapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.marvelapp.database.util.CharactersConverter
import com.example.watchedapp.database.dao.CharactersDao
import com.example.watchedapp.database.models.MarvelCharacterEntity

@Database(
    entities = [
        MarvelCharacterEntity::class
    ],
    version = 1
)
@TypeConverters(CharactersConverter::class)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
}