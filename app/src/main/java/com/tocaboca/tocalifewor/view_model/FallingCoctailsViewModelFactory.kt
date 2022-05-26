package com.tocaboca.tocalifewor.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tocaboca.tocalifewor.di.AppComponent

class FallingCoctailsViewModelFactory(
    private val app: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FallingCoctailsViewModel(app) as T
    }
}