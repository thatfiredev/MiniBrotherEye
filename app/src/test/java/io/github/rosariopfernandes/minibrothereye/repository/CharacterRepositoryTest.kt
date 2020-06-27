package io.github.rosariopfernandes.minibrothereye.repository

import io.github.rosariopfernandes.minibrothereye.data.CharacterDao
import io.github.rosariopfernandes.minibrothereye.model.Character
import io.github.rosariopfernandes.minibrothereye.network.CharacterService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class CharacterRepositoryTest {
    private val character1 = Character(id = 1) // cached
    private val character2 = Character(id = 2) // cached
    private val character3 = Character(id = 3) // network
    private val character4 = Character(id = 4) // network
    private val cachedCharacters = listOf(character1, character2)
    private val networkCharacters = listOf(character3, character4)

    private lateinit var mockCharacterDao: CharacterDao
    private lateinit var mockCharacterService: CharacterService

    private lateinit var repository: CharacterRepository

    @Before
    fun createRepository(): Unit = runBlocking {
        mockCharacterService = mock(CharacterService::class.java)
        mockCharacterDao = mock(CharacterDao::class.java)
        `when`(mockCharacterService.getCharacterList()).thenReturn(networkCharacters)
        `when`(mockCharacterService.getCharacterInfo(3)).thenReturn(character3)
        `when`(mockCharacterService.getCharacterInfo(4)).thenReturn(character4)

        `when`(mockCharacterDao.getAll()).thenReturn(cachedCharacters)
        `when`(mockCharacterDao.getInfo(1)).thenReturn(character1)
        `when`(mockCharacterDao.getInfo(2)).thenReturn(character2)
        repository = CharacterRepository.getInstance(mockCharacterDao, mockCharacterService)!!
    }


    @Test
    fun fetchCharacterInfo_returnsCharacterWithSpecificIdFromCache() = runBlocking {
        var actualCharacter = repository.fetchCharacterInfo(1)
        assertEquals(character1, actualCharacter)
        assertEquals(character1.id, actualCharacter.id)

        actualCharacter = repository.fetchCharacterInfo(2)
        assertEquals(character2, actualCharacter)
        assertEquals(character2.id, actualCharacter.id)
    }

    @Test
    fun fetchCharacterInfo_returnsCharacterWithSpecificIdFromNetwork() = runBlocking {
        var actualCharacter = repository.fetchCharacterInfo(3)
        assertEquals(character3, actualCharacter)
        assertEquals(character3.id, actualCharacter.id)

        actualCharacter = repository.fetchCharacterInfo(4)
        assertEquals(character4, actualCharacter)
        assertEquals(character4.id, actualCharacter.id)
    }

}