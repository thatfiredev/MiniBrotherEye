package io.github.rosariopfernandes.minibrothereye.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import io.github.rosariopfernandes.minibrothereye.data.CharacterPagingSource
import io.github.rosariopfernandes.minibrothereye.model.Character
import io.github.rosariopfernandes.minibrothereye.repository.CharacterRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ListViewModel @ViewModelInject constructor(
    private val repository: CharacterRepository
) : ViewModel() {
    private var _characters = MutableLiveData<PagingData<Character>>()
    val characters: LiveData<PagingData<Character>>
        get() = _characters

    init {
        viewModelScope.launch {
            _characters.value = getPagerLiveData()
        }
    }

    suspend fun refreshList() {
        _characters.value = getPagerLiveData(true)
    }

    private suspend fun getPagerLiveData(forceRefresh: Boolean = false): PagingData<Character> {
        return Pager(PagingConfig(pageSize = 4, prefetchDistance = 4)) {
            CharacterPagingSource(forceRefresh = forceRefresh, characterRepository = repository)
        }.flow.first()
    }
}