package com.tocaboca.tocalifewor.helpers

import android.app.Application
import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.facebook.applinks.AppLinkData
import com.tocaboca.tocalifewor.R
import com.tocaboca.tocalifewor.ext.appComponent
import javax.inject.Inject

class CoctailLinkHelper(private val app: Application) {
    @Inject
    lateinit var resources: Resources

    @Inject
    lateinit var appsFlyerLib: AppsFlyerLib

    private var isAf: Boolean = false
    private var isFb: Boolean = false
    var af: MutableMap<String, Any>? = null
    var fb: String? = null

    private val _isDataFetched: MutableLiveData<Boolean> = MutableLiveData()
    val isDataFetched: LiveData<Boolean> = _isDataFetched

    init {
        app.applicationContext.appComponent.inject(this)
    }

    fun loadData() {
        val appsFlyerConversionListener = object : AppsFlyerConversionListener {
            override fun onConversionDataSuccess(p0: MutableMap<String, Any>?) {
                af = p0
                isAf = true
                if (isFb) _isDataFetched.postValue(true)
            }

            override fun onConversionDataFail(p0: String?) {}

            override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {}

            override fun onAttributionFailure(p0: String?) {}

        }
        appsFlyerLib.init(
            resources.getString(R.string.apps_dev_key),
            appsFlyerConversionListener,
            app.applicationContext
        )
        appsFlyerLib.start(app.applicationContext)

        AppLinkData.fetchDeferredAppLinkData(app.applicationContext) { appLinkData ->
            fb = appLinkData?.targetUri.toString()
            isFb = true
            if (isAf) _isDataFetched.postValue(true)
        }
    }
}