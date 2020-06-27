package io.github.rosariopfernandes.minibrothereye.repository

import io.github.rosariopfernandes.minibrothereye.data.CharacterDao
import io.github.rosariopfernandes.minibrothereye.model.Character
import io.github.rosariopfernandes.minibrothereye.network.CharacterService

class CharacterRepository(
    private val characterDao: CharacterDao,
    private val characterService: CharacterService
) {

    companion object {
        @Volatile
        private var instance: CharacterRepository? = null

        fun getInstance(
            dao: CharacterDao,
            service: CharacterService
        ): CharacterRepository? {
            return instance ?: synchronized(CharacterRepository::class.java) {
                if (instance == null) {
                    instance = CharacterRepository(dao, service)
                }
                return instance
            }
        }
    }

    /**
     * Fetches 4 characters
     */
    suspend fun fetchCharacterPage(offset: Int): List<Character> {
        val charactersList: List<Character>
        val cachedCharacters = characterDao.get4Characters(offset)
        if (cachedCharacters.isEmpty()) {
            charactersList = characterService.getCharacterList()
            characterDao.insertAll(charactersList)
        }
        return characterDao.get4Characters(offset)
    }

    suspend fun fetchCharacterInfo(characterId: Int): Character {
        val characterInfo: Character
        val cachedCharacter = characterDao.getInfo(characterId)
        if (cachedCharacter != null) {
            characterInfo = cachedCharacter
        } else {
            characterInfo = characterService.getCharacterInfo(characterId)
            characterDao.insertCharacter(characterInfo)
        }
        return characterInfo
    }

}
