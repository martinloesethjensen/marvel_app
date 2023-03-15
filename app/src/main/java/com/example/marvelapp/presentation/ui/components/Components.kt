package com.example.marvelapp.presentation.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.marvelapp.domain.models.MarvelCharacter

@Composable
fun Center(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()
    ) {
        content()
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PosterGrid(
    modifier: Modifier = Modifier,
    marvelCharacters: List<MarvelCharacter>,
    onCardClick: (Int) -> Unit,
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
    ) {
        items(marvelCharacters) { marvelCharacter ->
            Box(modifier = Modifier.padding(8.dp)) {
                Card(
                    onClick = { onCardClick(marvelCharacter.id) },
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column {
                        SubcomposeAsyncImage(
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
                        Text(text = marvelCharacter.name)
                    }
                }
            }
        }
    }
}
