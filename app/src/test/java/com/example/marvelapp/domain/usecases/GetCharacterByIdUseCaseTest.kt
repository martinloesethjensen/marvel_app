package com.example.marvelapp.domain.usecases

import com.example.marvelapp.data.repositories.character.TestCharacterRepository
import com.example.marvelapp.domain.models.MarvelCharacter
import com.example.marvelapp.populatedMarvelCharacters
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetCharacterByIdUseCaseTest {
    private val characterRepository = TestCharacterRepository()

    val useCase = GetCharacterByIdUseCase(characterRepository)

    @Test
    fun shouldSuccessfully_FindCharacter_ById() = runTest {
        characterRepository.setCharactersResources(populatedMarvelCharacters(5))

        val marvelCharacter = useCase(5).first()

        assert(marvelCharacter.id == 5)
    }

    @Test
    fun shouldNot_FindCharacter_ById() = runTest {
        characterRepository.setCharactersResources(populatedMarvelCharacters(5))

        val marvelCharacter = useCase(100).first()

        assert(marvelCharacter == MarvelCharacter.empty)
    }
}
