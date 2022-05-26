package com.tocaboca.tocalifewor.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.tocaboca.tocalifewor.helpers.CoctailLinkHelper
import com.tocaboca.tocalifewor.ext.appComponent
import com.tocaboca.tocalifewor.helpers.CocktailLinkParser
import com.tocaboca.tocalifewor.helpers.OneSignalSender
import javax.inject.Inject

class FallingCoctailsViewModel(app: Application) : AndroidViewModel(app) {
    @Inject
    lateinit var coctailLinkHelper: CoctailLinkHelper
    @Inject
    lateinit var coctailLinkParser: CocktailLinkParser
    @Inject
    lateinit var oneSignalSender: OneSignalSender

    private val _cocktailLink: MutableLiveData<Boolean> = MutableLiveData()
    val coctailLink: LiveData<Boolean> get() = _cocktailLink

    private val coctailLinkHelperObserver = Observer<Boolean> { helperValue ->
        _cocktailLink.postValue(helperValue)
    }

    init {
        app.appComponent.inject(this)
        coctailLinkHelper.isDataFetched.observeForever(coctailLinkHelperObserver)
        coctailLinkHelper.loadData()
    }

    override fun onCleared() {
        super.onCleared()
        coctailLinkHelper.isDataFetched.removeObserver(coctailLinkHelperObserver)
    }

}