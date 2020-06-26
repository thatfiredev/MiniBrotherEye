package io.github.rosariopfernandes.minibrothereye.ui.list

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import io.github.rosariopfernandes.minibrothereye.data.AppDatabase
import io.github.rosariopfernandes.minibrothereye.model.Character
import io.github.rosariopfernandes.minibrothereye.network.CharacterService
import io.github.rosariopfernandes.minibrothereye.repository.CharacterRepository
import io.github.rosariopfernandes.minibrothereye.util.API_BASE_URL
import io.github.rosariopfernandes.minibrothereye.util.DataResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListViewModel(
    private val repository: CharacterRepository
) : ViewModel() {
    val charactersLiveData: LiveData<DataResult<List<Character>>> = liveData {
        emit(DataResult.InProgress)
        try {
            val characters = repository.fetchCharacterList()
            emit(DataResult.Success(characters))
        } catch (e: Exception) {
            emit(DataResult.Error(e))
        }
    }

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