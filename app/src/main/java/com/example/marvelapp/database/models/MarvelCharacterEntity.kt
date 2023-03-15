package com.example.watchedapp.database.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marvelapp.data.models.Thumbnail
import com.example.marvelapp.domain.models.MarvelCharacter

@Entity(tableName = "characters")
data class MarvelCharacterEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    @Embedded val thumbnail: Thumbnail?
)

fun MarvelCharacterEntity.asExternalModel() = MarvelCharacter(
    id,
    name,
    description,
    thumbnail,
)
