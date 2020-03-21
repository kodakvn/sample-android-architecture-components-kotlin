package com.kodak.sampleandroidarchitecturecomponents

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.realm.Realm
import io.realm.RealmResults

class NoteViewModel : ViewModel() {
    private val realm: Realm by lazy { Realm.getDefaultInstance() }
    private var repository: NoteRepository = NoteRepository(realm)
    private var allNotes: LiveData<RealmResults<Note>>

    init {
        allNotes = repository.getAllNotes()
    }

    fun insert(note: Note) {
        repository.insert(note)
    }

    fun update(note: Note) {
        repository.update(note)
    }

    fun delete(id: Long) {
        repository.delete(id)
    }

    fun deleteAllNotes() {
        repository.deleteAllNotes()
    }

    fun getAllNotes(): LiveData<RealmResults<Note>> {
        return allNotes
    }

    override fun onCleared() {
        realm.close()
        super.onCleared()
    }
}