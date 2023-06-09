package com.example.marvelapp.presentation.character_details

import com.example.marvelapp.domain.models.MarvelCharacter

sealed interface CharacterDetailsUiState {
    object Loading : CharacterDetailsUiState
    object Failure : CharacterDetailsUiState
    data class Success(
        val data: MarvelCharacter,
    ) : CharacterDetailsUiState
}