package com.example.marvelapp.data.models

import com.example.marvelapp.extensions.toHttpsPrefix
import kotlinx.serialization.Serializable

@Serializable
data class Thumbnail(
    val extension: String? = null,
    val path: String? = null,

    val pathWithExt: String = "${path?.toHttpsPrefix()}.$extension"
)
