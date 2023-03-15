package com.example.marvelapp.data.repositories.character

import com.example.marvelapp.data.models.asEntity
import com.example.marvelapp.data.models.asExternalModel
import com.example.marvelapp.domain.models.MarvelCharacter
import com.example.marvelapp.network.CharacterNetworkDataSource
import com.example.watchedapp.database.dao.CharactersDao
import com.example.watchedapp.database.models.asExternalModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import java.time.Instant
import javax.inject.Inject

data class CharacterQuery(
    val limit: Int = 10,
    val ts: Instant = Instant.now()
)

interface CharacterRepository {
    fun fetchCharacters(query: CharacterQuery): Flow<List<MarvelCharacter>>
}

@OptIn(FlowPreview::class)
class CharacterRepositoryImpl @Inject constructor(
    private val network: CharacterNetworkDataSource,
    private val dao: CharactersDao,
) : CharacterRepository {
    override fun fetchCharacters(query: CharacterQuery): Flow<List<MarvelCharacter>> {
        val remote = flow {
            try {
                val characters = network.fetchCharacters(query).data.results
                dao.upsertCharacterItems(characters.map { it.asEntity() })
                emit(characters.map { it.asExternalModel() })
            } catch (_: Throwable) {
            }
        }

        val local = flow {
            val characters = dao.getCharacterEntities().first()
            emit(characters.map { it.asExternalModel() })
        }

        return flowOf(remote, local).flattenMerge()
    }
}