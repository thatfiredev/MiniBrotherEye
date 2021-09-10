package io.github.rosariopfernandes.minibrothereye.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.rosariopfernandes.minibrothereye.model.Character

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters LIMIT 4 OFFSET :offset")
    suspend fun get4Characters(offset: Int): List<Character>

    @Query("SELECT * FROM characters WHERE id=:id")
    suspend fun getInfo(id: Int): Character?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: Character)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<Character>)

    @Query("SELECT COUNT(id) FROM characters")
    suspend fun getCharacterCount(): Int

    @Query("SELECT * FROM characters")
    fun pagingSource(): PagingSource<Int, Character>

    @Query("DELETE FROM characters")
    suspend fun clearAll()

}