package com.kodak.sampleandroidarchitecturecomponents

import android.app.Application
import io.realm.Realm

class App : Application() {
    companion object {
        private var instance: App? = null

        fun getInstance(): App = instance!!
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}