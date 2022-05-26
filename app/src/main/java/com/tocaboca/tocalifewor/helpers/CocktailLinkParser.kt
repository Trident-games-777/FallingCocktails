package com.tocaboca.tocalifewor.helpers

import android.app.Application
import android.content.res.Resources
import android.net.Uri
import com.appsflyer.AppsFlyerLib
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.tocaboca.tocalifewor.R
import com.tocaboca.tocalifewor.ext.appComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class CocktailLinkParser(private val app: Application) {
    @Inject
    lateinit var baseUri: Uri

    @Inject
    lateinit var resources: Resources

    init {
        app.appComponent.inject(this)
    }

    suspend fun parse(fbLink: String?, afLink: MutableMap<String, Any>?): String {
        val id = withContext(Dispatchers.Default) {
            AdvertisingIdClient
                .getAdvertisingIdInfo(app.applicationContext).id.toString()
        }

        val url = baseUri.buildUpon().apply {
            appendQueryParameter(
                resources.getString(R.string.secure_get_parametr),
                resources.getString(R.string.secure_key)
            )
            appendQueryParameter(
                resources.getString(R.string.dev_tmz_key),
                TimeZone.getDefault().id
            )
            appendQueryParameter(resources.getString(R.string.gadid_key), id)
            appendQueryParameter(resources.getString(R.string.deeplink_key), fbLink)
            appendQueryParameter(
                resources.getString(R.string.source_key),
                afLink?.get("media_source").toString()
            )
            appendQueryParameter(
                resources.getString(R.string.af_id_key),
                AppsFlyerLib.getInstance().getAppsFlyerUID(app.applicationContext)
            )
            appendQueryParameter(
                resources.getString(R.string.adset_id_key),
                afLink?.get("adset_id").toString()
            )
            appendQueryParameter(
                resources.getString(R.string.campaign_id_key),
                afLink?.get("campaign_id").toString()
            )
            appendQueryParameter(
                resources.getString(R.string.app_campaign_key),
                afLink?.get("campaign").toString()
            )
            appendQueryParameter(
                resources.getString(R.string.adset_key),
                afLink?.get("adset").toString()
            )
            appendQueryParameter(
                resources.getString(R.string.adgroup_key),
                afLink?.get("adgroup").toString()
            )
            appendQueryParameter(
                resources.getString(R.string.orig_cost_key),
                afLink?.get("orig_cost").toString()
            )
            appendQueryParameter(
                resources.getString(R.string.af_siteid_key),
                afLink?.get("af_siteid").toString()
            )
        }.toString()
        return url
    }
}