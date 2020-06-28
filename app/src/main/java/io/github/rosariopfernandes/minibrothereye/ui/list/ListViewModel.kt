package io.github.rosariopfernandes.minibrothereye.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import io.github.rosariopfernandes.minibrothereye.data.CharacterPagingSource
import io.github.rosariopfernandes.minibrothereye.repository.CharacterRepository

class ListViewModel @ViewModelInject constructor(
    private val repository: CharacterRepository
) : ViewModel() {
    val flow = Pager(PagingConfig(pageSize = 4, prefetchDistance = 4)) {
        CharacterPagingSource(repository)
    }.flow.cachedIn(viewModelScope)
}