package com.kodak.sampleandroidarchitecturecomponents.home

import com.kodak.sampleandroidarchitecturecomponents.di.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [MainActivityModule::class])
interface MainActivityComponent {
    fun inject(activity: MainActivity): MainActivity
}