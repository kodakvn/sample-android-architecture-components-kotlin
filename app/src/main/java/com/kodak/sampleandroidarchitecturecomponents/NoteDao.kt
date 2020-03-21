package com.kodak.sampleandroidarchitecturecomponents

import androidx.lifecycle.LiveData
import io.realm.RealmResults

interface NoteDao {

    fun insert(note: Note)

    fun update(note: Note)

    fun delete(id: Long)

    fun deleteAlLNotes()

    fun getAllNotes(): LiveData<RealmResults<Note>>
}