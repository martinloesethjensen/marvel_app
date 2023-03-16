package com.example.marvelapp.data.repositories.character

import com.example.marvelapp.domain.models.MarvelCharacter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.mapLatest

@OptIn(ExperimentalCoroutinesApi::class)
class TestCharacterRepository : CharacterRepository {
    private val characterResourceFlow: MutableSharedFlow<List<MarvelCharacter>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun fetchCharacters(query: CharacterQuery): Flow<List<MarvelCharacter>> {
        TODO("Not yet implemented")
    }

    override fun getCharacterById(id: Int): Flow<MarvelCharacter> {
        return characterResourceFlow.mapLatest { characters ->
            characters.find { it.id == id } ?: MarvelCharacter.empty
        }
    }

    fun setCharactersResources(items: List<MarvelCharacter>) {
        characterResourceFlow.tryEmit(items)
    }
}