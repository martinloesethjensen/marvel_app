package com.example.marvelapp.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.marvelapp.presentation.character_details.CharacterDetailsRoute

const val characterDetailsNavigationRoute = "character/details"

fun NavController.navigateToCharacterDetails(
    id: Int,
    navOptions: NavOptions? = null,
) {
    this.navigate("$characterDetailsNavigationRoute/$id", navOptions)
}

fun NavGraphBuilder.characterDetailsScreen(
    onBackClick: () -> Unit,
) {
    composable(
        route = "$characterDetailsNavigationRoute/{id}",
        arguments = listOf(navArgument("id") { type = NavType.IntType })
    ) {
        CharacterDetailsRoute(
            onBackClick,
        )
    }
}