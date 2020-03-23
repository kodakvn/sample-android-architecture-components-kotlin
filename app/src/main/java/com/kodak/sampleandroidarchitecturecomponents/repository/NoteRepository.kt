package com.kodak.sampleandroidarchitecturecomponents.repository

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.kodak.sampleandroidarchitecturecomponents.repository.model.Note
import com.kodak.sampleandroidarchitecturecomponents.repository.db.NoteDao
import com.kodak.sampleandroidarchitecturecomponents.repository.db.NoteDatabase

class NoteRepository {
    private var noteDao: NoteDao
    private var allNotes: LiveData<List<Note>>

    constructor(context: Context) {
        val database = NoteDatabase.getInstance(context)
        noteDao = database.noteDao()
        allNotes = noteDao.getAllNotes()
    }

    fun insert(note: Note) {
        InsertNoteAsyncTask(noteDao).execute(note)
    }

    fun update(note: Note) {
        UpdateNoteAsyncTask(noteDao).execute(note)
    }

    fun delete(note: Note) {
        DeleteNoteAsyncTask(noteDao).execute(note)
    }

    fun deleteAllNotes() {
        DeleteAllNotesAsyncTask(noteDao).execute()
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }

    companion object {
        private class InsertNoteAsyncTask: AsyncTask<Note, Void, Int> {
            private var noteDao: NoteDao

            constructor(noteDao: NoteDao) {
                this.noteDao = noteDao
            }

            override fun doInBackground(vararg params: Note?): Int {
                noteDao.insert(params[0] as Note)
                return 0
            }
        }

        private class UpdateNoteAsyncTask: AsyncTask<Note, Void, Int> {
            private var noteDao: NoteDao

            constructor(noteDao: NoteDao) {
                this.noteDao = noteDao
            }

            override fun doInBackground(vararg params: Note?): Int {
                noteDao.update(params[0] as Note)
                return 0
            }
        }

        private class DeleteNoteAsyncTask: AsyncTask<Note, Void, Int> {
            private var noteDao: NoteDao

            constructor(noteDao: NoteDao) {
                this.noteDao = noteDao
            }

            override fun doInBackground(vararg params: Note?): Int {
                noteDao.delete(params[0] as Note)
                return 0
            }
        }

        private class DeleteAllNotesAsyncTask: AsyncTask<Void, Void, Int> {
            private var noteDao: NoteDao

            constructor(noteDao: NoteDao) {
                this.noteDao = noteDao
            }

            override fun doInBackground(vararg params: Void?): Int {
                noteDao.deleteAlLNotes()
                return 0
            }
        }
    }
}