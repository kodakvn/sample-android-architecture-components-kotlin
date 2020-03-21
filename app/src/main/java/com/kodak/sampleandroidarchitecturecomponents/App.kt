package com.kodak.sampleandroidarchitecturecomponents

import android.app.Application

class App : Application() {
    companion object {
        private var instance: App? = null

        fun getInstance(): App = instance!!
    }

    init {
        instance = this
    }
}