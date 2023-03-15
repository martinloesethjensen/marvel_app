package com.example.marvelapp.domain.models

import com.example.marvelapp.data.models.Thumbnail

data class MarvelCharacter(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val thumbnail: Thumbnail? = null
)


