package com.thindie.bureaub

import android.app.Application
import com.thindie.bureaub.di.DependenciesProvider

class BureauBApplication : Application() {

    lateinit var dependenciesProvider: DependenciesProvider
        private set

    fun setProvider(dependenciesProvider: DependenciesProvider) {
        if (!::dependenciesProvider.isInitialized) this.dependenciesProvider = dependenciesProvider
    }

    override fun onCreate() {
        super.onCreate()
        DependenciesProvider.inject(this)
    }
}
