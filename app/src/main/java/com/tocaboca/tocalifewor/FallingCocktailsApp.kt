package com.tocaboca.tocalifewor

import android.app.Application
import com.tocaboca.tocalifewor.di.AppComponent
import com.tocaboca.tocalifewor.di.DaggerAppComponent

class FallingCocktailsApp : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}