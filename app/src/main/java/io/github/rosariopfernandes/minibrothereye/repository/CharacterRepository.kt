package io.github.rosariopfernandes.minibrothereye.repository

import io.github.rosariopfernandes.minibrothereye.model.Character
import io.github.rosariopfernandes.minibrothereye.network.CharacterService

class CharacterRepository(
    private val characterService: CharacterService
) {

    companion object {
        @Volatile
        private var instance: CharacterRepository? = null

        fun getInstance(service: CharacterService): CharacterRepository? {
            return instance ?: synchronized(CharacterRepository::class.java) {
                if (instance == null) {
                    instance = CharacterRepository(service)
                }
                return instance
            }
        }
    }

    suspend fun fetchCharacterList(): List<Character> {
        return characterService.getCharacterList()
    }

    suspend fun fetchCharacterInfo(characterId: Int): Character {
        return characterService.getCharacterInfo(characterId)
    }

}
