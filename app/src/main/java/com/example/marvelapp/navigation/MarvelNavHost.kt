package com.example.marvelapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun MarvelNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = homeNavigationRoute,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeScreen(navController::navigateToCharacterDetails)
        characterDetailsScreen(navController::popBackStack)
    }
}