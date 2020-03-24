package com.kodak.sampleandroidarchitecturecomponents

import android.app.Application

class App : Application() {

    private lateinit var appComponent: AppComponent

    companion object {
        private var instance: App? = null

        fun getInstance(): App = instance!!
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()

        initAppComponent()
    }

    fun getAppComponent(): AppComponent = appComponent

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}