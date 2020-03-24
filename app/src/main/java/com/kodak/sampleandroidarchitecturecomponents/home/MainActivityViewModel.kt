package com.kodak.sampleandroidarchitecturecomponents.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kodak.sampleandroidarchitecturecomponents.App
import com.kodak.sampleandroidarchitecturecomponents.repository.NoteRepository
import com.kodak.sampleandroidarchitecturecomponents.repository.model.Note
import javax.inject.Inject

class MainActivityViewModel : ViewModel() {

    @Inject
    lateinit var repository: NoteRepository

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