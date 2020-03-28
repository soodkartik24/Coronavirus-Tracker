package com.covid.coronavirusapp.di

import com.covid.coronavirusapp.CoronaApp
import com.covid.coronavirusapp.base.BaseActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(app: CoronaApp)
    fun inject(activity: BaseActivity)
}