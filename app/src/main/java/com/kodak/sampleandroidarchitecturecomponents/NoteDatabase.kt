package com.kodak.sampleandroidarchitecturecomponents

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {
    companion object {
        private lateinit var instance: NoteDatabase

        @Synchronized fun getInstance(context: Context): NoteDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()
            }
            return instance
        }

        private var roomCallback: RoomDatabase.Callback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance).execute()
            }
        }

        private class PopulateDbAsyncTask: AsyncTask<Void, Void, Int> {
            private var noteDao: NoteDao

            constructor(db: NoteDatabase) {
                this.noteDao = db.noteDao()
            }

            override fun doInBackground(vararg params: Void?): Int {
                noteDao.insert(Note(title = "Title 1", description = "Description 1", priority = 1))
                noteDao.insert(Note(title = "Title 2", description = "Description 2", priority = 2))
                noteDao.insert(Note(title = "Title 3", description = "Description 3", priority = 3))
                return 0
            }
        }
    }

    abstract fun noteDao(): NoteDao
}