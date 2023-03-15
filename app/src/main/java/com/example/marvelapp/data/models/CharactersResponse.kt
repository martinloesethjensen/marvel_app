package com.example.marvelapp.data.models

import com.example.marvelapp.domain.models.MarvelCharacter
import com.example.watchedapp.database.models.MarvelCharacterEntity
import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponse(
    val code: Int = 0,
    val status: String = "",
    val data: CharacterDataResponse,
)

@Serializable
data class CharacterDataResponse(
    val count: Int = 0,
    val limit: Int = 0,
    val offset: Int = 0,
    val total: Int = 0,
    val results: List<CharacterResponse> = listOf()
)

@Serializable
data class CharacterResponse(
    val id: Int = 0,
    val modified: String? = null,
    val name: String = "",
    val description: String = "",
    val thumbnail: Thumbnail? = null
)

fun CharacterResponse.asExternalModel() = MarvelCharacter(
    id, name, description, thumbnail
)

fun CharacterResponse.asEntity() = MarvelCharacterEntity(
    id, name, description, thumbnail
)