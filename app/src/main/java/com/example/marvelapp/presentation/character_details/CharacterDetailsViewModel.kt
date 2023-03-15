package com.example.marvelapp.presentation.character_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.domain.core.result.Result
import com.example.marvelapp.domain.core.result.asResult
import com.example.marvelapp.domain.usecases.GetCharacterByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val uiState: StateFlow<CharacterDetailsUiState> =
        getCharacterByIdUseCase(savedStateHandle.get<Int>("id") ?: 0)
            .asResult()
            .map {
                when (it) {
                    is Result.Error -> CharacterDetailsUiState.Failure
                    Result.Loading -> CharacterDetailsUiState.Loading
                    is Result.Success -> CharacterDetailsUiState.Success(it.data)
                }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(3_000),
                initialValue = CharacterDetailsUiState.Loading,
            )
}