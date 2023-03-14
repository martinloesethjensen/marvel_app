package com.example.marvelapp.presentation.home

sealed interface HomeUiState {
    object Loading : HomeUiState
    object Failure : HomeUiState
    // TODO: replace
    data class Success(val data: String = "") : HomeUiState
}