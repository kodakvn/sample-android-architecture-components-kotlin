package com.kodak.sampleandroidarchitecturecomponents

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import io.realm.Realm
import io.realm.RealmResults

class NoteRepository {
    private var noteDao: NoteDao
    private var allNotes: LiveData<RealmResults<Note>>

    constructor(realm: Realm) {
        noteDao = NoteDaoImpl.getInstance(realm)
        allNotes = noteDao.getAllNotes()
    }

    fun insert(note: Note) {
        noteDao.insert(note)
        //InsertNoteAsyncTask(noteDao).execute(note)
    }

    fun update(note: Note) {
        noteDao.update(note)
        //UpdateNoteAsyncTask(noteDao).execute(note)
    }

    fun delete(id: Long) {
        noteDao.delete(id)
        //DeleteNoteAsyncTask(noteDao).execute(id)
    }

    fun deleteAllNotes() {
        noteDao.deleteAlLNotes()
        //DeleteAllNotesAsyncTask(noteDao).execute()
    }

    fun getAllNotes(): LiveData<RealmResults<Note>> {
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

        private class DeleteNoteAsyncTask: AsyncTask<Long, Void, Int> {
            private var noteDao: NoteDao

            constructor(noteDao: NoteDao) {
                this.noteDao = noteDao
            }

            override fun doInBackground(vararg params: Long?): Int {
                noteDao.delete(params[0] as Long)
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