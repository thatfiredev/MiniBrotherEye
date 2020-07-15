package io.github.rosariopfernandes.minibrothereye.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.rosariopfernandes.minibrothereye.model.RemoteKey

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: RemoteKey)

    @Query("SELECT * FROM remote_keys WHERE currentCursor = :cursor")
    suspend fun remoteKeyByQuery(cursor: String): RemoteKey

    @Query("DELETE FROM remote_keys")
    suspend fun deleteAll()
}
