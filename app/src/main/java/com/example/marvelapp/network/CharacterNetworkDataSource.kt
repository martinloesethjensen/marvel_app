package com.example.marvelapp.network

import com.example.marvelapp.data.models.CharactersResponse
import com.example.marvelapp.data.repositories.character.CharacterQuery

interface CharacterNetworkDataSource {
    suspend fun fetchCharacters(query: CharacterQuery): CharactersResponse
}