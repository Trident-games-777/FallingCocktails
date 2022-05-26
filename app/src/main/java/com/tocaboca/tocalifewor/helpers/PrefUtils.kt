package com.tocaboca.tocalifewor.helpers

import android.app.Application
import android.content.Context
import androidx.core.content.edit

private const val PREF_NAME = "prefs"
private const val FIRST_LAUNCH_KEY = "first_launch_key"
private const val URL_KEY = "url_key"

class PrefUtils(private val app: Application) {

    fun isFirst(): Boolean {
        val pref = app.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val first = pref.getBoolean(FIRST_LAUNCH_KEY, true)
        return if (first) {
            pref.edit {
                putBoolean(FIRST_LAUNCH_KEY, false)
            }
            true
        } else false
    }

    fun putUrl(newUrl: String) {
        val pref = app.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val url = pref.getString(URL_KEY, "0")
        when {
            url!!.startsWith('0') -> {
                pref.edit {
                    putString(URL_KEY, "1$newUrl")
                }
            }
            url.startsWith('1') -> {
                pref.edit {
                    putString(URL_KEY, "2$newUrl")
                }
            }
        }
    }

    fun getUrl(): String {
        val pref = app.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return pref.getString(URL_KEY, " empty url")!!.substring(1)
    }
}