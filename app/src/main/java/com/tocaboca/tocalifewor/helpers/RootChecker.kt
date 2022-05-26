package com.tocaboca.tocalifewor.helpers

import android.app.Application
import android.content.res.Resources
import android.provider.Settings
import com.tocaboca.tocalifewor.R
import com.tocaboca.tocalifewor.ext.appComponent
import java.io.File
import javax.inject.Inject

class RootChecker(private val app: Application) {
    @Inject
    lateinit var resources: Resources

    init {
        app.appComponent.inject(this)
    }

    fun isRooted(): Boolean = root() || adb() == "1"

    private fun root(): Boolean {
        val dirsArray: Array<String> = resources.getStringArray(R.array.dirs)
        try {
            for (dir in dirsArray) {
                if (File(dir + "su").exists()) return true
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
        return false
    }

    private fun adb(): String {
        return Settings.Global.getString(app.contentResolver, Settings.Global.ADB_ENABLED)
            ?: "null"
    }
}