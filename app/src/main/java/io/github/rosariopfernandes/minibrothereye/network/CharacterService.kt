package io.github.rosariopfernandes.minibrothereye.network

import io.github.rosariopfernandes.minibrothereye.model.Character
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {

    @GET("id/{id}/")
    suspend fun getCharacterInfo(@Path("id") id: Int): Character

    @GET("cursor/{next_cursor}/")
    suspend fun getPaginatedList(@Path("next_cursor") next_cursor: String): ApiResponse
}