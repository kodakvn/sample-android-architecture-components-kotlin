package com.kodak.sampleandroidarchitecturecomponents

import com.kodak.sampleandroidarchitecturecomponents.home.MainActivityComponent
import com.kodak.sampleandroidarchitecturecomponents.home.MainActivityModule
import com.kodak.sampleandroidarchitecturecomponents.repository.RepositoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class])
interface AppComponent {
    fun inject(module: MainActivityModule): MainActivityComponent
}