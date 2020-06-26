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

    suspend fun fetchCharacterList(): List<Character> {
        val charactersList: List<Character>
        val cachedCharacters = characterDao.getAll()
        if (cachedCharacters.isNotEmpty()) {
            charactersList = cachedCharacters
        } else {
            charactersList = characterService.getCharacterList()
            characterDao.insertAll(charactersList)
        }
        return charactersList
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
