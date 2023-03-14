package com.example.marvelapp.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.data.repositories.character.CharacterQuery
import com.example.marvelapp.domain.core.result.Result
import com.example.marvelapp.domain.core.result.asResult
import com.example.marvelapp.domain.usecases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
) : ViewModel() {
    val uiState: StateFlow<HomeUiState> =
        getCharactersUseCase(CharacterQuery())
            .asResult()
            .map {
                Log.d("Result", it.toString())
                when (it) {
                    is Result.Error -> HomeUiState.Failure
                    Result.Loading -> HomeUiState.Loading
                    is Result.Success -> HomeUiState.Success(it.data)
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = HomeUiState.Loading
            )
}