package io.github.rosariopfernandes.minibrothereye.ui.list

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import io.github.rosariopfernandes.minibrothereye.data.AppDatabase
import io.github.rosariopfernandes.minibrothereye.data.CharacterPagingSource
import io.github.rosariopfernandes.minibrothereye.network.CharacterService
import io.github.rosariopfernandes.minibrothereye.repository.CharacterRepository
import io.github.rosariopfernandes.minibrothereye.util.API_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListViewModel(
    private val repository: CharacterRepository
) : ViewModel() {
    val flow = Pager(PagingConfig(pageSize = 4, prefetchDistance = 4)) {
        CharacterPagingSource(repository)
    }.flow.cachedIn(viewModelScope)
}

internal class ListViewModelFactory(
    private val context: Context
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        // Local Persistence
        val characterDao = AppDatabase.getInstance(context).characterDao()

        // Networking
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(CharacterService::class.java)

        val repository = CharacterRepository.getInstance(characterDao, service)
        return modelClass.getConstructor(CharacterRepository::class.java)
            .newInstance(repository)
    }
}