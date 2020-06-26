package io.github.rosariopfernandes.minibrothereye.network

import io.github.rosariopfernandes.minibrothereye.model.Character
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {

    @GET("all/")
    suspend fun getCharacterList(): List<Character>

    @GET("id/{id}/")
    suspend fun getCharacterInfo(@Path("id") id: Int): Character
}