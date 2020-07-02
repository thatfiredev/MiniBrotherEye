package io.github.rosariopfernandes.minibrothereye.repository

import io.github.rosariopfernandes.minibrothereye.data.CharacterDao
import io.github.rosariopfernandes.minibrothereye.model.Character
import io.github.rosariopfernandes.minibrothereye.network.CharacterService
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterDao: CharacterDao,
    private val characterService: CharacterService
) : CharacterRepository {

    /**
     * Fetches 4 characters
     */
    override suspend fun fetchCharacterPage(
        offset: Int,
        forceRefresh: Boolean
    ): List<Character> {
        val charactersList: List<Character>
        val cachedCharacters = characterDao.get4Characters(offset)
        if (cachedCharacters.isEmpty() || forceRefresh) {
            charactersList = characterService.getCharacterList()
            characterDao.insertAll(charactersList)
        }
        return characterDao.get4Characters(offset)
    }

    override suspend fun fetchCharacterInfo(
        characterId: Int,
        forceRefresh: Boolean
    ): Character {
        val characterInfo: Character
        val cachedCharacter = characterDao.getInfo(characterId)
        if (cachedCharacter != null && !forceRefresh) {
            characterInfo = cachedCharacter
        } else {
            characterInfo = characterService.getCharacterInfo(characterId)
            characterDao.insertCharacter(characterInfo)
        }
        return characterInfo
    }

}
