package io.github.rosariopfernandes.minibrothereye.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import io.github.rosariopfernandes.minibrothereye.model.Character
import io.github.rosariopfernandes.minibrothereye.network.CharacterService
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val characterDao: CharacterDao,
    private val characterService: CharacterService
) : RemoteMediator<Int, Character>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Character>
    ): MediatorResult {
        return try {

            val nextCursor = when (loadType) {
                LoadType.REFRESH -> "-1"
                LoadType.PREPEND -> return MediatorResult.Success(true)
                LoadType.APPEND -> {
                    val lastCharacter = state.lastItemOrNull() ?: return MediatorResult.Success(true)
                    "${lastCharacter.id}"
                }
            }

            val response = characterService.getPaginatedList(nextCursor)
            characterDao.insertAll(response.characters)

            MediatorResult.Success(response.next_cursor == 0)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}