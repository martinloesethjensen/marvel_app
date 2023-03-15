package com.example.marvelapp.presentation.character_details

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun CharacterDetailsRoute(
    onBackClick: () -> Unit,
    //marvelCharacter: MarvelCharacter?,
) {
    CharacterDetailsScreen(
        onBackClick = onBackClick,
        //marvelCharacter = marvelCharacter!!,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CharacterDetailsScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    //marvelCharacter: MarvelCharacter,
) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text("marvelCharacter.name") },
            navigationIcon = {
                IconButton(onBackClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                        //contentDescription = stringResource(R.string.backButtonContentDescription),
                    )
                }
            }
        )
    }, content = { innerPadding ->
        Text(text = "marvelCharacter.description", modifier.padding(innerPadding))
    })
}