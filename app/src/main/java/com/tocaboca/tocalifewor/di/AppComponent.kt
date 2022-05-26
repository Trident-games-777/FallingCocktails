package com.tocaboca.tocalifewor.di

import android.app.Application
import com.tocaboca.tocalifewor.helpers.CocktailLinkParser
import com.tocaboca.tocalifewor.helpers.CoctailLinkHelper
import com.tocaboca.tocalifewor.helpers.RootChecker
import com.tocaboca.tocalifewor.screen.LoadingScreenActivity
import com.tocaboca.tocalifewor.view_model.FallingCoctailsViewModel
import com.tocaboca.tocalifewor.web.WebActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface AppComponent {
    fun inject(webActivity: WebActivity)
    fun inject(loadingScreenActivity: LoadingScreenActivity)
    fun inject(rootChecker: RootChecker)
    fun inject(viewModel: FallingCoctailsViewModel)
    fun inject(coctailLinkHelper: CoctailLinkHelper)
    fun inject(cocktailLinkParser: CocktailLinkParser)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}