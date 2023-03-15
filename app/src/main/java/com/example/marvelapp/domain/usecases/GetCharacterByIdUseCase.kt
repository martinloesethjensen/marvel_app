package com.example.marvelapp.domain.usecases

import com.example.marvelapp.data.repositories.character.CharacterRepository
import com.example.marvelapp.domain.models.MarvelCharacter
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
) {
    operator fun invoke(id: Int): Flow<MarvelCharacter> =
        characterRepository.getCharacterById(id)
}