package io.github.rosariopfernandes.minibrothereye.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.rosariopfernandes.minibrothereye.model.Character
import io.github.rosariopfernandes.minibrothereye.repository.CharacterRepository
import java.net.UnknownHostException

class CharacterPagingSource(
    private val characterRepository: CharacterRepository
) : PagingSource<Int, Character>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val nextPageNumber  = params.key ?: 0
            val characterPage = characterRepository.fetchCharacterPage(nextPageNumber)
            LoadResult.Page(
                data = characterPage,
                prevKey = null,
                nextKey = nextPageNumber + 4
            )
        } catch (e: UnknownHostException) {
            // Unable to connect to the network
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return null
    }
}