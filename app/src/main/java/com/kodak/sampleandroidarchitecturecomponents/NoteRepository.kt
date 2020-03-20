package com.kodak.sampleandroidarchitecturecomponents

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class NoteRepository {
    private lateinit var noteDao: NoteDao
    private lateinit var allNotes: LiveData<List<Note>>

    constructor(application: Application) {
        val database = NoteDatabase.getInstance(application)
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