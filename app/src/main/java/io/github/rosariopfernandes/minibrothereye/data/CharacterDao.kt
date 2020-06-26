package io.github.rosariopfernandes.minibrothereye.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.github.rosariopfernandes.minibrothereye.model.Character

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters")
    suspend fun getAll(): List<Character>

    @Query("SELECT * FROM characters WHERE id=:id")
    suspend fun getInfo(id: Int): Character?

    @Insert
    suspend fun insertCharacter(character: Character)

    @Insert
    suspend fun insertAll(characters: List<Character>)
}