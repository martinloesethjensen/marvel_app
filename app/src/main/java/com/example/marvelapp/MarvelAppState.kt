package com.example.marvelapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberMarvelAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): MarvelAppState {
    return remember(navController, coroutineScope) {
        MarvelAppState(navController, coroutineScope)
    }
}

@Stable
class MarvelAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
)