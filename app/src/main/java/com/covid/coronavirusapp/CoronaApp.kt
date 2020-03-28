package com.covid.coronavirusapp

import android.app.Application
import com.covid.coronavirusapp.di.ApplicationComponent
import com.covid.coronavirusapp.di.ApplicationModule
import com.covid.coronavirusapp.di.DaggerApplicationComponent

class CoronaApp: Application() {


    override fun onCreate() {
        super.onCreate()
        instance = this

        initDagger()
    }

    private fun initDagger() {
        component =
            DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    companion object {
        lateinit var instance: CoronaApp
            private set
        lateinit var component: ApplicationComponent
            private set
    }
}