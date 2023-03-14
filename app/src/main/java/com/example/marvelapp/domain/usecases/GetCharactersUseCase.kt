package com.example.marvelapp.domain.usecases

import com.example.marvelapp.data.repositories.character.CharacterQuery
import com.example.marvelapp.data.repositories.character.CharacterRepository
import com.example.marvelapp.domain.MarvelCharacter
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
) {
    operator fun invoke(query: CharacterQuery): Flow<List<MarvelCharacter>> =
        characterRepository.fetchCharacters(query)
}