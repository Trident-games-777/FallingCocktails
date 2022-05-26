package com.tocaboca.tocalifewor.helpers

import android.util.Log
import com.onesignal.OneSignal

class OneSignalSender {
    fun send(fbLink: String?, afLink: MutableMap<String, Any>?) {
        val campaign = afLink?.get("campaign").toString()

        if (campaign == "null" && (fbLink == "null" || fbLink == null)) {
            OneSignal.sendTag("key2", "organic")
        } else if (fbLink != "null") {
            OneSignal.sendTag("key2", fbLink?.replace("myapp://", "")?.substringBefore("/"))
        } else if (campaign != "null") {
            OneSignal.sendTag("key2", campaign.substringBefore("_"))
        }
    }
}