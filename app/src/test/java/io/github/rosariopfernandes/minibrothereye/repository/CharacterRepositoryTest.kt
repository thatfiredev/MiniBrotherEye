package io.github.rosariopfernandes.minibrothereye.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.rosariopfernandes.minibrothereye.model.Character
import io.github.rosariopfernandes.minibrothereye.network.CharacterService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE, sdk = [28])
class CharacterRepositoryTest {
    private val character1 = Character(id = 1)
    private val character2 = Character(id = 2)
    private val character3 = Character(id = 3)
    private val characters = listOf(character1, character2, character3)

    private lateinit var mockCharacterService: CharacterService

    private lateinit var repository: CharacterRepository

    @Before
    fun createRepository(): Unit = runBlocking {
        mockCharacterService = mock(CharacterService::class.java)
        `when`(mockCharacterService.getCharacterList()).thenReturn(characters)
        `when`(mockCharacterService.getCharacterInfo(1)).thenReturn(character1)
        `when`(mockCharacterService.getCharacterInfo(2)).thenReturn(character2)
        repository = CharacterRepository.getInstance(mockCharacterService)!!
    }

    @Test
    fun fetchCharactersList_returnsListOfCharacters() = runBlocking {
        val actualCharacters = repository.fetchCharacterList()
        assertEquals(characters, actualCharacters)
    }

    @Test
    fun fetchCharacterInfo_returnsCharacterWithSpecificId() = runBlocking {
        var actualCharacter = repository.fetchCharacterInfo(1)
        assertEquals(character1, actualCharacter)
        assertEquals(character1.id, actualCharacter.id)

        actualCharacter = repository.fetchCharacterInfo(2)
        assertEquals(character2, actualCharacter)
        assertEquals(character2.id, actualCharacter.id)
    }
}