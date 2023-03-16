package com.example.marvelapp

import com.example.marvelapp.domain.models.MarvelCharacter

fun populatedMarvelCharacters(count: Int = 1): List<MarvelCharacter> =
    (1..count).map(::testMarvelCharacter)

fun testMarvelCharacter(id: Int = 0) = MarvelCharacter(id = id)