package io.github.rosariopfernandes.minibrothereye.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.github.rosariopfernandes.minibrothereye.network.CharacterService
import io.github.rosariopfernandes.minibrothereye.util.API_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    fun provideCharacterService(): CharacterService {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(CharacterService::class.java)
    }
}