package com.kodak.sampleandroidarchitecturecomponents.home

import androidx.lifecycle.ViewModelProvider
import com.kodak.sampleandroidarchitecturecomponents.di.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule(private val activity: MainActivity) {

    @Provides
    @ActivityScope
    fun provideMainActivityViewModel(): MainActivityViewModel = ViewModelProvider(activity)[MainActivityViewModel::class.java]
}