@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.marvelapp.presentation.character_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.marvelapp.domain.models.MarvelCharacter
import com.example.marvelapp.presentation.home.ErrorScreen
import com.example.marvelapp.presentation.home.LoadingScreen
import com.example.marvelapp.presentation.ui.components.Center

@Composable
internal fun CharacterDetailsRoute(
    onBackClick: () -> Unit,
    characterDetailsViewModel: CharacterDetailsViewModel = hiltViewModel()
) {
    val uiState by characterDetailsViewModel.uiState.collectAsStateWithLifecycle()
    CharacterDetailsScreen(
        onBackClick = onBackClick,
        uiState = uiState,
    )
}

@Composable
internal fun CharacterDetailsScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    uiState: CharacterDetailsUiState,
) {
    when (uiState) {
        CharacterDetailsUiState.Failure -> ErrorScreen(modifier)
        CharacterDetailsUiState.Loading -> LoadingScreen(modifier)
        is CharacterDetailsUiState.Success -> SuccessScreen(
            onBackClick = onBackClick,
            marvelCharacter = uiState.data,
        )
    }
}

@Composable
private fun SuccessScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    marvelCharacter: MarvelCharacter,
) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(marvelCharacter.name) },
            navigationIcon = {
                IconButton(onBackClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        )
    }, content = { innerPadding ->
        Column(modifier.padding(innerPadding).fillMaxWidth()) {
            SubcomposeAsyncImage(
                modifier = modifier.fillMaxWidth(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(marvelCharacter.thumbnail?.pathWithExt)
                    .crossfade(300)
                    .build(),
                loading = {
                    Center(modifier) {
                        CircularProgressIndicator()
                    }
                },
                error = {
                    Center(modifier) {
                        Text(text = marvelCharacter.name)
                    }
                },
                contentDescription = marvelCharacter.name
            )
            Text(text = marvelCharacter.description, modifier.padding(innerPadding))
        }
    })
}