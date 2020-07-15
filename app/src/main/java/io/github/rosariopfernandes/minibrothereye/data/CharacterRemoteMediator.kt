package io.github.rosariopfernandes.minibrothereye.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import io.github.rosariopfernandes.minibrothereye.model.Character
import io.github.rosariopfernandes.minibrothereye.repository.CharacterRepository
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val repository: CharacterRepository
) : RemoteMediator<Int, Character>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Character>
    ): MediatorResult {
        return try {
            val lastCharacter = state.lastItemOrNull()

            val nextCursor = when (loadType) {
                // When loading for the first time
                LoadType.REFRESH -> "-1"
                // We're not prepending data, let's ignore this
                LoadType.PREPEND -> return MediatorResult.Success(true)
                // For pages 2..N
                LoadType.APPEND -> {
                    if (lastCharacter == null) {
                        return MediatorResult.Success(true)
                    }
                    val remoteKey = repository.getRemoteKeyForPage("${lastCharacter.id}")
                    if (remoteKey.nextCursor == 0) {
                        return MediatorResult.Success(true)
                    }
                    "${remoteKey.nextCursor}"
                }
            }

            val response = repository.getPaginatedList(nextCursor)

            // If the user wants fresh data, let's delete all the existing data
            if (loadType == LoadType.REFRESH) {
                repository.cleanDatabase()
            }

            // save the key for this page
            val lastResponseCharacter = response.characters.last()
            repository.insertOrReplaceKey(lastResponseCharacter.id, response.next_cursor)

            // Save all characters from server to the database
            repository.insertCharacters(response.characters)

            MediatorResult.Success(response.next_cursor == 0)

        } catch (e: IOException) {
            // User is probably not connected to the internet
            // Let's try to provide cached data
            if (repository.getCharacterCount() > 0) {
                MediatorResult.Success(true)
            } else {
                MediatorResult.Error(e)
            }
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}