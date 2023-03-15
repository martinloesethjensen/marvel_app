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
    val limit: Int = 25,
    val ts: Instant = Instant.now()
)

interface CharacterRepository {
    fun fetchCharacters(query: CharacterQuery): Flow<List<MarvelCharacter>>
    fun getCharacterById(id: Int): Flow<MarvelCharacter>
}

@OptIn(FlowPreview::class)
class CharacterRepositoryImpl @Inject constructor(
    private val network: CharacterNetworkDataSource,
    private val dao: CharactersDao,
) : CharacterRepository {
    override fun fetchCharacters(query: CharacterQuery): Flow<List<MarvelCharacter>> {
        val local = flow {
            val characters = dao.getCharacterEntities().first()
            emit(characters.map { it.asExternalModel() })
        }

        val remote = flow {
            try {
                val characters = network.fetchCharacters(query).data.results.sortedBy { it.name }
                dao.upsertCharacterItems(characters.map { it.asEntity() })
                emit(characters.map { it.asExternalModel() })
            } catch (_: Throwable) {
            }
        }

        return flowOf(local, remote).flattenMerge()
    }

    override fun getCharacterById(id: Int): Flow<MarvelCharacter> =
        dao.getCharacterEntity(id).map { it.asExternalModel() }
}