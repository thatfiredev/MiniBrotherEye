package io.github.rosariopfernandes.minibrothereye.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKey(
    @PrimaryKey
    val currentCursor: Int,
    val nextCursor: Int
)