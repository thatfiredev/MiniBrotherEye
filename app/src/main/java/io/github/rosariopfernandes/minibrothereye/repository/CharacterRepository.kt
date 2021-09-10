package io.github.rosariopfernandes.minibrothereye.repository

import androidx.paging.PagingSource
import io.github.rosariopfernandes.minibrothereye.model.Character
import io.github.rosariopfernandes.minibrothereye.model.RemoteKey
import io.github.rosariopfernandes.minibrothereye.network.ApiResponse

interface CharacterRepository {
    fun getPagingSource(): PagingSource<Int, Character>

    suspend fun fetchCharacterInfo(characterId: Int): Character

    suspend fun getPaginatedList(nextCursor: String): ApiResponse

    suspend fun insertCharacters(characters: List<Character>)

    suspend fun getCharacterCount(): Int

    suspend fun insertOrReplaceKey(page: Int, nextCursor: Int)

    suspend fun getRemoteKeyForPage(page: String): RemoteKey

    suspend fun cleanDatabase()
}