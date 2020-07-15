package io.github.rosariopfernandes.minibrothereye.repository

import androidx.paging.PagingSource
import io.github.rosariopfernandes.minibrothereye.data.CharacterDao
import io.github.rosariopfernandes.minibrothereye.data.RemoteKeyDao
import io.github.rosariopfernandes.minibrothereye.model.Character
import io.github.rosariopfernandes.minibrothereye.model.RemoteKey
import io.github.rosariopfernandes.minibrothereye.network.ApiResponse
import io.github.rosariopfernandes.minibrothereye.network.CharacterService
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val remoteKeyDao: RemoteKeyDao,
    private val characterDao: CharacterDao,
    private val characterService: CharacterService
) : CharacterRepository {

    override fun getPagingSource(): PagingSource<Int, Character> = characterDao.pagingSource()

    override suspend fun getPaginatedList(nextCursor: String): ApiResponse {
        return characterService.getPaginatedList(nextCursor)
    }

    override suspend fun fetchCharacterInfo(characterId: Int): Character {
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

    override suspend fun insertCharacters(characters: List<Character>) {
        characterDao.insertAll(characters)
    }

    override suspend fun getCharacterCount(): Int = characterDao.getCharacterCount()

    override suspend fun insertOrReplaceKey(page: Int, nextCursor: Int) {
        remoteKeyDao.insertOrReplace(RemoteKey(page, nextCursor))
    }

    override suspend fun getRemoteKeyForPage(page: String): RemoteKey {
        return remoteKeyDao.remoteKeyByQuery(page)
    }

    override suspend fun cleanDatabase() {
        remoteKeyDao.deleteAll()
        characterDao.clearAll()
    }

}
