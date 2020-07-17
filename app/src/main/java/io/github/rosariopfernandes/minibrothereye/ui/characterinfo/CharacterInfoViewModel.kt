package io.github.rosariopfernandes.minibrothereye.ui.characterinfo

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.rosariopfernandes.minibrothereye.model.Character
import io.github.rosariopfernandes.minibrothereye.repository.CharacterRepository
import kotlinx.coroutines.launch

class CharacterInfoViewModel @ViewModelInject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _characterInfo = MutableLiveData<Character>()
    val characterInfo: LiveData<Character>
        get() = _characterInfo

    private val _exception = MutableLiveData<Exception?>()
    val exception: LiveData<Exception?>
        get() = _exception

    init {
        _characterInfo.value = Character()
        _isLoading.value = false
        _exception.value = null
    }

    fun fetchCharacterInfo(characterId: Int, forceRefresh: Boolean) {
        viewModelScope.launch {
            // There's no point in fetching again if it's already loading
            if (_isLoading.value == false) {
                _isLoading.value = true
                try {
                    val character = repository.fetchCharacterInfo(characterId, forceRefresh)
                    _characterInfo.value = character
                } catch (e: Exception) {
                    e.printStackTrace()
                    _exception.value = e
                }
                _isLoading.value = false
            }
        }
    }
}