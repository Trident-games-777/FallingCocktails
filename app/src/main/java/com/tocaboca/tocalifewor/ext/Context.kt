package com.tocaboca.tocalifewor.ext

import android.content.Context
import com.tocaboca.tocalifewor.FallingCocktailsApp
import com.tocaboca.tocalifewor.di.AppComponent

val Context.appComponent: AppComponent
    get() = when (this) {
        is FallingCocktailsApp -> appComponent
        else -> this.applicationContext.appComponent
    }