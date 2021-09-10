package io.github.rosariopfernandes.minibrothereye.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.github.rosariopfernandes.minibrothereye.data.CharacterDao
import io.github.rosariopfernandes.minibrothereye.data.RemoteKeyDao
import io.github.rosariopfernandes.minibrothereye.network.CharacterService
import io.github.rosariopfernandes.minibrothereye.repository.CharacterRepository
import io.github.rosariopfernandes.minibrothereye.repository.CharacterRepositoryImpl

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideCharacterRepository(
        remoteKeyDao: RemoteKeyDao,
        characterDao: CharacterDao,
        characterService: CharacterService
    ): CharacterRepository {
        return CharacterRepositoryImpl(remoteKeyDao, characterDao, characterService)
    }
}