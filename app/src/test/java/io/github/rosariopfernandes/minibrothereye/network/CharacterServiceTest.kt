package io.github.rosariopfernandes.minibrothereye.network

import io.github.rosariopfernandes.minibrothereye.util.API_BASE_URL
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharacterServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private val mockResponseCharacterInfo = """
            {
                "id": 20,
                "name": "Amazo",
                "slug": "20-amazo",
                "powerstats": {
                    "intelligence": 63,
                    "strength": 100,
                    "speed": 83,
                    "durability": 100,
                    "power": 100,
                    "combat": 100
                },
                "appearance": {
                    "gender": "Male",
                    "race": "Android",
                    "height": [
                        "8'5",
                        "257 cm"
                    ],
                    "weight": [
                        "385 lb",
                        "173 kg"
                    ],
                    "eyeColor": "Red",
                    "hairColor": "-"
                },
                "biography": {
                    "fullName": "",
                    "alterEgos": "No alter egos found.",
                    "aliases": [
                        "Professor Ivos Amazing Android",
                        "Timazo",
                        "Humazo",
                        "Hourmazo"
                    ],
                    "placeOfBirth": "-",
                    "firstAppearance": "Brave and the Bold #30 (July, 1960)",
                    "publisher": "DC Comics",
                    "alignment": "bad"
                },
                "work": {
                    "occupation": "-",
                    "base": "-"
                },
                "connections": {
                    "groupAffiliation": "Formerly the Secret Society of Super Villains",
                    "relatives": "Professor Ivo (creator), Kid Amazo (cyborg offspring)"
                },
                "images": {
                "xs": "https://cdn.rawgit.com/akabab/superhero-api/0.2.0/api/images/xs/20-amazo.jpg",
                "sm": "https://cdn.rawgit.com/akabab/superhero-api/0.2.0/api/images/sm/20-amazo.jpg",
                "md": "https://cdn.rawgit.com/akabab/superhero-api/0.2.0/api/images/md/20-amazo.jpg",
                "lg": "https://cdn.rawgit.com/akabab/superhero-api/0.2.0/api/images/lg/20-amazo.jpg"
                }
            }
    """.trimIndent()

    private val mockResponseAllCharacters = "[$mockResponseCharacterInfo]"


    /**
     * Helper method to get a CharacterService instance
     */
    private fun getCharacterService(): CharacterService {
        val baseUrl = mockWebServer.url(API_BASE_URL)
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(CharacterService::class.java)
    }

    @Test
    fun getCharactersList_canBeParsed() = runBlocking {
        mockWebServer = MockWebServer()
        mockWebServer.enqueue(MockResponse().setBody(mockResponseAllCharacters))
        mockWebServer.start()

        val service = getCharacterService()

        val characters = service.getCharacterList()
        assertFalse(characters.isEmpty())
        assertTrue(characters[0].id == 20)
        assertTrue(characters[0].name == "Amazo")

        mockWebServer.shutdown()
    }

    @Test
    fun getCharactersInfo_canBeParsed() = runBlocking {
        mockWebServer = MockWebServer()
        mockWebServer.enqueue(MockResponse().setBody(mockResponseCharacterInfo))
        mockWebServer.start()

        val service = getCharacterService()

        val characterInfo = service.getCharacterInfo(20)
        assertTrue(characterInfo.id == 20)

        mockWebServer.shutdown()
    }
}