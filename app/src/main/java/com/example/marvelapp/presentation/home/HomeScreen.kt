package com.example.marvelapp.presentation.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.marvelapp.presentation.ui.components.Center

@Composable
internal fun HomeRoute(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        modifier = modifier,
        homeUiState = homeUiState,
    )
}

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    homeUiState: HomeUiState,
) {
    when (homeUiState) {
        HomeUiState.Loading -> LoadingScreen(modifier)
        HomeUiState.Failure -> ErrorScreen(modifier)
        is HomeUiState.Success -> SuccessScreen(
            modifier = modifier,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text("Marvel")
            },
        )
    }, content = { innerPadding ->
        EmptyHomeBody(modifier.padding(innerPadding))
    })
}

@Composable
fun EmptyHomeBody(modifier: Modifier = Modifier) {
    Center(modifier) {
        Text(stringResource(com.example.marvelapp.R.string.empty_home_text))
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Center(modifier) {
        CircularProgressIndicator(modifier = modifier)
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Center(modifier) {
        Text(stringResource(com.example.marvelapp.R.string.error_text))
    }
}


