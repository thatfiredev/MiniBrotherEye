package io.github.rosariopfernandes.minibrothereye.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import io.github.rosariopfernandes.minibrothereye.data.CharacterRemoteMediator
import io.github.rosariopfernandes.minibrothereye.repository.CharacterRepository

class ListViewModel @ViewModelInject constructor(
    private val repository: CharacterRepository
) : ViewModel() {
    val pager = Pager(
        PagingConfig(pageSize = 10, prefetchDistance = 10),
        remoteMediator = CharacterRemoteMediator(repository)
    ) {
        repository.getPagingSource()
    }

    val flow = pager.flow.cachedIn(viewModelScope)
}