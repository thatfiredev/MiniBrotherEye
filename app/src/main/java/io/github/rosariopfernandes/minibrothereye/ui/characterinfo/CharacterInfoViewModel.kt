package io.github.rosariopfernandes.minibrothereye.ui.characterinfo

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import io.github.rosariopfernandes.minibrothereye.repository.CharacterRepository
import io.github.rosariopfernandes.minibrothereye.util.DataResult

class CharacterInfoViewModel @ViewModelInject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    fun fetchCharacterInfo(characterId: Int) = liveData {
        emit(DataResult.InProgress)
        try {
            val character = repository.fetchCharacterInfo(characterId)
            emit(DataResult.Success(character))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataResult.Error(e))
        }
    }
}