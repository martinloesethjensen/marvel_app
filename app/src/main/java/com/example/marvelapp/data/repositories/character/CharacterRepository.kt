package com.example.marvelapp.data.repositories.character

import com.example.marvelapp.data.models.asExternalModel
import com.example.marvelapp.domain.MarvelCharacter
import com.example.marvelapp.network.CharacterNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.Instant
import javax.inject.Inject

data class CharacterQuery(
    val limit: Int = 10,
    val ts: Instant = Instant.now()
)

interface CharacterRepository {
    fun fetchCharacters(query: CharacterQuery): Flow<List<MarvelCharacter>>
}

class RemoteCharacterRepository @Inject constructor(
    private val network: CharacterNetworkDataSource,
) : CharacterRepository {
    override fun fetchCharacters(query: CharacterQuery) =
        flow {
            emit(network.fetchCharacters(query).data.results.map {
                it.asExternalModel()
            })
        }
}