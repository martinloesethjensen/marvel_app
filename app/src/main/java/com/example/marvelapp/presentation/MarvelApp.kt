package com.example.marvelapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.marvelapp.MarvelAppState
import com.example.marvelapp.navigation.MarvelNavHost
import com.example.marvelapp.rememberMarvelAppState

@Composable
fun MarvelApp(
    appState: MarvelAppState = rememberMarvelAppState()
) {
    Scaffold(modifier = Modifier) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            MarvelNavHost(appState.navController)
        }
    }
}