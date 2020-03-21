package com.kodak.sampleandroidarchitecturecomponents

import androidx.lifecycle.LiveData
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort

class NoteDaoImpl(private val realm: Realm): NoteDao {

    companion object {
        private var instance: NoteDaoImpl? = null

        @Synchronized fun getInstance(realm: Realm): NoteDao {
            if (instance == null) {
                instance = NoteDaoImpl(realm)
            }
            return instance!!
        }
    }

    override fun insert(note: Note) {
        realm.executeTransactionAsync {
            val lastId = (it.where(Note::class.java).max("id") ?: 0).toLong()
            note.id = lastId+1L
            it.insert(note)
        }
    }

    override fun update(note: Note) {
        realm.executeTransactionAsync {
            it.insertOrUpdate(note)
        }
    }

    override fun delete(id: Long) {
        realm.executeTransactionAsync {
            val result = it.where(Note::class.java).equalTo("id", id).findFirst()
            result?.deleteFromRealm()
        }
    }

    override fun deleteAlLNotes() {
        realm.executeTransactionAsync {
            val result = it.where(Note::class.java).findAll()
            result.deleteAllFromRealm()
        }
    }

    override fun getAllNotes(): LiveData<RealmResults<Note>> {
        return realm.where(Note::class.java)
            .sort("priority", Sort.DESCENDING)
            .findAllAsync()
            .asLiveData()
    }
}