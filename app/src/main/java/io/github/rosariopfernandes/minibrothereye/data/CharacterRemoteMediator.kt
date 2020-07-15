package io.github.rosariopfernandes.minibrothereye.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import io.github.rosariopfernandes.minibrothereye.model.Character
import io.github.rosariopfernandes.minibrothereye.network.CharacterService
import okio.IOException
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val characterDao: CharacterDao,
    private val networkService: CharacterService
) : RemoteMediator<Int, Character>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Character>
    ): MediatorResult {
        return try {

            val response = networkService.getCharacterList()

            MediatorResult.Success(
                endOfPaginationReached = response.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}