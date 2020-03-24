package com.kodak.sampleandroidarchitecturecomponents.repository

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.kodak.sampleandroidarchitecturecomponents.repository.db.NoteDao
import com.kodak.sampleandroidarchitecturecomponents.repository.db.NoteDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class RepositoryModule(application: Application) {

    private var noteDatabase: NoteDatabase =
        Room.databaseBuilder(application.applicationContext, NoteDatabase::class.java, "note_database")
            .fallbackToDestructiveMigration()
            //.addCallback(NoteDatabase.roomCallback)
            .build()

    @Provides
    @Singleton
    fun provideNoteDatabase(): NoteDatabase = noteDatabase

    @Provides
    @Singleton
    fun provideNoteDao(db: NoteDatabase): NoteDao = db.noteDao()

    @Provides
    @Singleton
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository = NoteRepository(noteDao)
}