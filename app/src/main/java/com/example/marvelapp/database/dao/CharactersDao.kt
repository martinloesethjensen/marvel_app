package com.example.watchedapp.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.watchedapp.database.models.MarvelCharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {
    @Query(
        value = """
            SELECT * FROM characters
            ORDER BY name ASC
        """
    )
    fun getCharacterEntities(): Flow<List<MarvelCharacterEntity>>

    @Query(
        value = """
            SELECT * FROM characters
            WHERE id = :id
        """
    )
    fun getCharacterEntity(id: Int): Flow<MarvelCharacterEntity>

    @Upsert
    suspend fun upsertCharacterItems(marvelCharacters: List<MarvelCharacterEntity>)
}