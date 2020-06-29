package io.github.rosariopfernandes.minibrothereye.repository

import io.github.rosariopfernandes.minibrothereye.model.Character

interface CharacterRepository {
    suspend fun fetchCharacterPage(offset: Int): List<Character>
    suspend fun fetchCharacterInfo(characterId: Int): Character
}