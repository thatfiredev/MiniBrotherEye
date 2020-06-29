package io.github.rosariopfernandes.minibrothereye.util

import io.github.rosariopfernandes.minibrothereye.model.Character
import io.github.rosariopfernandes.minibrothereye.repository.CharacterRepository

class FakeCharacterRepository(
    private val forceException: Boolean = false
) : CharacterRepository {
    override suspend fun fetchCharacterPage(offset: Int): List<Character> {
        if (forceException) {
            throw NullPointerException()
        }
        return listOf(Character(id = 1), Character(id = 2), Character(id = 3), Character(id = 4))
    }

    override suspend fun fetchCharacterInfo(characterId: Int): Character {
        if (forceException) {
            throw NullPointerException()
        }
        return Character(id = characterId)
    }
}