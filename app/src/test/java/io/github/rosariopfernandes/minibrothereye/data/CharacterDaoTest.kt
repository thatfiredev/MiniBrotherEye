package io.github.rosariopfernandes.minibrothereye.data

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import io.github.rosariopfernandes.minibrothereye.model.Character
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE, sdk = [28])
class CharacterDaoTest {

    private val context = InstrumentationRegistry.getInstrumentation().context
    private lateinit var database: AppDatabase

    private val character1 = Character(id = 1)
    private val character2 = Character(id = 2)
    private val characters = listOf(character1, character2)

    @Before
    fun createDatabase() {
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .build()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun insertCharacter_savesData() = runBlocking {
        database.characterDao().insertCharacter(character1)
        val savedCharacters = database.characterDao().get4Characters(0)
        assertTrue(savedCharacters.isNotEmpty())
    }

    @Test
    fun insertCharacters_savesData() = runBlocking {
        var savedCharacters = database.characterDao().get4Characters(0)
        assertTrue(savedCharacters.isEmpty())
        database.characterDao().insertAll(characters)
        savedCharacters = database.characterDao().get4Characters(0)
        assertTrue(savedCharacters.isNotEmpty())
    }

    @Test
    fun getAll_retrievesData() = runBlocking {
        for (character in characters) {
            database.characterDao().insertCharacter(character)
        }
        val retrievedCharacters = database.characterDao().get4Characters(0)
        assertTrue(retrievedCharacters == characters)
    }

    @Test
    fun getInfo_retrievesData() = runBlocking {
        database.characterDao().insertCharacter(character1)
        val retrievedCharacter = database.characterDao().getInfo(1)
        assertTrue(retrievedCharacter == character1)
    }
}