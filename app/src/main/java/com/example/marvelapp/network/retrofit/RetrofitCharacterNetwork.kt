package com.example.marvelapp.network.retrofit

import com.example.marvelapp.Secrets
import com.example.marvelapp.data.models.CharactersResponse
import com.example.marvelapp.data.repositories.character.CharacterQuery
import com.example.marvelapp.network.CharacterNetworkDataSource
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

private interface RetrofitCharacterNetworkApi {
    @GET("characters")
    suspend fun fetchCharacters(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String = Secrets.publicKey,
        @Query("hash") hash: String,
        @Query("limit") limit: Int = 10,
    ): CharactersResponse
}

private const val ApiBaseUrl = "https://gateway.marvel.com/v1/public/"

class RetrofitCharacterNetwork @Inject constructor(
    networkJson: Json,
    okHttpCallFactory: Call.Factory,
) : CharacterNetworkDataSource {
    private val networkApi = Retrofit.Builder()
        .baseUrl(ApiBaseUrl)
        .callFactory(okHttpCallFactory)
        .addConverterFactory(
            @OptIn(ExperimentalSerializationApi::class)
            networkJson.asConverterFactory(MediaType.parse("application/json")!!)
        )
        .build()
        .create(RetrofitCharacterNetworkApi::class.java)

    override suspend fun fetchCharacters(query: CharacterQuery): CharactersResponse {
        return networkApi.fetchCharacters(
            ts = query.ts.epochSecond.toString(),
            hash = Secrets.generateHash(query.ts),
        )
    }
}