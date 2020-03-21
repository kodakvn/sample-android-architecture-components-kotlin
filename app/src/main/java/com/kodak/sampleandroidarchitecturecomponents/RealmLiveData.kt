package com.kodak.sampleandroidarchitecturecomponents

import androidx.lifecycle.LiveData
import io.realm.RealmChangeListener
import io.realm.RealmModel
import io.realm.RealmResults

class RealmLiveData <T : RealmModel>(val results: RealmResults<T>) : LiveData<RealmResults<T>>() {

    private val listener = RealmChangeListener<RealmResults<T>> { results -> value = results }

    override fun onActive() {
        results.addChangeListener(listener)
    }

    override fun onInactive() {
        results.removeChangeListener(listener)
    }
}