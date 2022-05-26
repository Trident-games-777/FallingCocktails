package com.tocaboca.tocalifewor.di

import android.app.Application
import android.content.res.Resources
import android.net.Uri
import androidx.core.net.toUri
import com.appsflyer.AppsFlyerLib
import com.tocaboca.tocalifewor.helpers.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    companion object {
        private const val BASE_URL = "feedchest13.monster/cocktails.php"
    }

    @Provides
    fun provideAppsFlyerLib(application: Application): AppsFlyerLib = AppsFlyerLib.getInstance()

    @Provides
    @Singleton
    fun provideCocktailLink(application: Application): CoctailLinkHelper =
        CoctailLinkHelper(application)

    @Provides
    fun provideCocktailLinkParser(application: Application): CocktailLinkParser =
        CocktailLinkParser(application)

    @Provides
    fun provideRootChecker(application: Application): RootChecker = RootChecker(application)

    @Provides
    fun providePrefUtils(application: Application): PrefUtils = PrefUtils(application)

    @Provides
    fun provideOneSignalSender(): OneSignalSender = OneSignalSender()

    @Provides
    fun providesBaseUri(): Uri = BASE_URL.toUri()

    @Provides
    fun provideResources(application: Application): Resources = application.resources
}