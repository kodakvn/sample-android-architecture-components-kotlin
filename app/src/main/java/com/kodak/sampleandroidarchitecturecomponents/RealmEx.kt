package com.kodak.sampleandroidarchitecturecomponents

import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmResults

fun <T: RealmModel> RealmResults<T>.asLiveData() = RealmLiveData<T>(this)

fun Realm.noteDao(): NoteDao = NoteDaoImpl.getInstance(this)