package com.covid.coronavirusapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.load.model.GlideUrl
import com.google.firebase.analytics.FirebaseAnalytics
import com.covid.coronavirusapp.di.ViewModelFactory
import com.covid.coronavirusapp.CoronaApp
import com.covid.coronavirusapp.network.ApiClient
import javax.inject.Inject

abstract class BaseActivity: AppCompatActivity() {

    @Inject
    lateinit var apiClient: ApiClient

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoronaApp.component.inject(this)
    }
}