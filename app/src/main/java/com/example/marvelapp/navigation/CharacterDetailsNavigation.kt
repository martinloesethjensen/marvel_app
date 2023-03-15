package com.example.marvelapp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.marvelapp.domain.models.MarvelCharacter
import com.example.marvelapp.presentation.character_details.CharacterDetailsRoute

const val characterDetailsNavigationRoute = "character/details/{character}"

fun NavController.navigateToCharacterDetails(
    marvelCharacter: MarvelCharacter,
    navOptions: NavOptions? = null
) {
    //this.currentBackStackEntry?.arguments?.putParcelable("character", marvelCharacter)
    this.navigate(characterDetailsNavigationRoute, navOptions)
}

fun NavGraphBuilder.characterDetailsScreen(
    onBackClick: () -> Unit,
) {
    composable(route = characterDetailsNavigationRoute) {
        CharacterDetailsRoute(
            onBackClick,
            //it.arguments?.getParcelable<MarvelCharacter>("character")
        )
    }
}