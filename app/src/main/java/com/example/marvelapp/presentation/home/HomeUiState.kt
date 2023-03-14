package com.example.marvelapp.presentation.home

import com.example.marvelapp.domain.MarvelCharacter

sealed interface HomeUiState {
    object Loading : HomeUiState
    object Failure : HomeUiState
    data class Success(
        val data: List<MarvelCharacter> = listOf()
    ) : HomeUiState
}