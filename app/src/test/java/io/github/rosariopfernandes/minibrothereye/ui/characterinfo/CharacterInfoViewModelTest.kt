package io.github.rosariopfernandes.minibrothereye.ui.characterinfo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.rosariopfernandes.minibrothereye.model.Character
import io.github.rosariopfernandes.minibrothereye.util.FakeCharacterRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.NullPointerException
import kotlin.Exception

@RunWith(AndroidJUnit4::class)
class CharacterInfoViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun fetchCharacterInfo_canGenerateException() {
        val fakeRepository = FakeCharacterRepository(forceException = true)
        val viewModel = CharacterInfoViewModel(fakeRepository)

        val observer = Observer<Exception?> {}
        try {
            viewModel.exception.observeForever(observer)
            viewModel.fetchCharacterInfo(1)
            val exception = viewModel.exception.value!!
            assertTrue(exception is NullPointerException)
        } finally {
            viewModel.exception.removeObserver(observer)
        }
    }

    @Test
    fun fetchCharacterInfo_canGetCharacterInfo() {
        val fakeRepository = FakeCharacterRepository()
        val viewModel = CharacterInfoViewModel(fakeRepository)

        val observer = Observer<Character> {}
        try {
            viewModel.characterInfo.observeForever(observer)
            viewModel.fetchCharacterInfo(1)
            val character = viewModel.characterInfo.value!!
            assertEquals(1, character.id)
        } finally {
            viewModel.characterInfo.removeObserver(observer)
        }
    }
}