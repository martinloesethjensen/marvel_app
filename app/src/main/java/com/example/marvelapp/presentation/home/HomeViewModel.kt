package com.example.marvelapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.domain.core.result.Result
import com.example.marvelapp.domain.core.result.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    val uiState: StateFlow<HomeUiState> =
        flowOf(false)
            .asResult()
            .map {
                when(it) {
                    is Result.Error -> HomeUiState.Failure
                    Result.Loading -> HomeUiState.Loading
                    is Result.Success -> HomeUiState.Success()
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = HomeUiState.Loading
            )
}