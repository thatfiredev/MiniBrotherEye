package io.github.rosariopfernandes.minibrothereye.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.rosariopfernandes.minibrothereye.model.Character
import io.github.rosariopfernandes.minibrothereye.model.RemoteKey
import io.github.rosariopfernandes.minibrothereye.util.StringListConverters

@Database(entities = [Character::class, RemoteKey::class], version = 3, exportSchema = false)
@TypeConverters(StringListConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}
