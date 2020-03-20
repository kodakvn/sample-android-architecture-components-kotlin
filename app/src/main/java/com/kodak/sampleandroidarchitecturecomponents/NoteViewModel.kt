package com.kodak.sampleandroidarchitecturecomponents

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class NoteViewModel(context: Context) : ViewModel() {
    private var repository: NoteRepository = NoteRepository(context)
    private var allNotes: LiveData<List<Note>>

    init {
        allNotes = repository.getAllNotes()
    }

    fun insert(note: Note) {
        repository.insert(note)
    }

    fun update(note: Note) {
        repository.update(note)
    }

    fun delete(note: Note) {
        repository.delete(note)
    }

    fun deleteAllNotes() {
        repository.deleteAllNotes()
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }
}