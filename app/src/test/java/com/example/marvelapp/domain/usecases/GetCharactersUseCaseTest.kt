package com.example.marvelapp.domain.usecases

import com.example.marvelapp.data.repositories.character.CharacterQuery
import com.example.marvelapp.data.repositories.character.TestCharacterRepository
import com.example.marvelapp.populatedMarvelCharacters
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetCharactersUseCaseTest {
    private val characterRepository = TestCharacterRepository()

    val useCase = GetCharactersUseCase(characterRepository)

    @Test
    fun shouldSuccessfully_Get10Characters() = runTest {
        val query = CharacterQuery(limit = 10)

        characterRepository.setCharactersResources(populatedMarvelCharacters(query.limit))

        val marvelCharacters = useCase(query).first()

        assert(marvelCharacters.count() == query.limit)
    }

    @Test
    fun shouldSuccessfully_Get5Characters() = runTest {
        val query = CharacterQuery(limit = 5)

        characterRepository.setCharactersResources(populatedMarvelCharacters(query.limit * 2))

        val marvelCharacters = useCase(query).first()

        assert(marvelCharacters.count() == 5)
    }

    @Test
    fun shouldSuccessfully_GetCharactersAvailable() = runTest {
        val query = CharacterQuery(limit = 5)

        characterRepository.setCharactersResources(populatedMarvelCharacters(1))

        val marvelCharacters = useCase(query).first()

        assert(marvelCharacters.count() == 1)
    }

    @Test
    fun shouldNot_FindAnyCharacters() = runTest {
        characterRepository.setCharactersResources(listOf())

        val query = CharacterQuery(limit = 10)

        val marvelCharacters = useCase(query).first()

        assert(marvelCharacters.none())
    }
}
