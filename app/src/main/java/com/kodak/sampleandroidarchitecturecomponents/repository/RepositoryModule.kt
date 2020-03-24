package com.kodak.sampleandroidarchitecturecomponents.repository

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideNoteRepository(context: Context): NoteRepository = NoteRepository(context)
}