package com.tocaboca.tocalifewor.screen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.gastudio.gabottleloading.library.GABottleLoadingView
import com.tocaboca.tocalifewor.R
import com.tocaboca.tocalifewor.ext.appComponent
import com.tocaboca.tocalifewor.game.GameActivity
import com.tocaboca.tocalifewor.helpers.PrefUtils
import com.tocaboca.tocalifewor.helpers.RootChecker
import com.tocaboca.tocalifewor.view_model.FallingCoctailsViewModel
import com.tocaboca.tocalifewor.view_model.FallingCoctailsViewModelFactory
import com.tocaboca.tocalifewor.web.WebActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoadingScreenActivity : AppCompatActivity() {
    private lateinit var coctailViewModel: FallingCoctailsViewModel

    @Inject
    lateinit var rootChecker: RootChecker

    @Inject
    lateinit var prefUtils: PrefUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_screen)
        findViewById<GABottleLoadingView>(R.id.loading_view).performAnimation()
        appComponent.inject(this)

        if (rootChecker.isRooted()) {
            startGame()
        } else {
            if (prefUtils.isFirst()) {
                val factory = FallingCoctailsViewModelFactory(application)
                coctailViewModel =
                    ViewModelProvider(this, factory)[FallingCoctailsViewModel::class.java]
                coctailViewModel.coctailLink.observe(this) {
                    lifecycleScope.launch {
                        with(coctailViewModel) {
                            val url = coctailLinkParser.parse(
                                coctailLinkHelper.fb,
                                coctailLinkHelper.af
                            )
                            oneSignalSender.send(
                                coctailLinkHelper.fb,
                                coctailLinkHelper.af
                            )
                            prefUtils.putUrl(url)
                            startWeb(url)
                        }
                    }
                }
            } else {
                startWeb(prefUtils.getUrl())
            }
        }
    }

    private fun startWeb(url: String) {
        val intent = Intent(this, WebActivity::class.java)
        intent.putExtra("url_extra", url)
        startActivity(intent)
        finish()
    }

    private fun startGame() {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        findViewById<GABottleLoadingView>(R.id.loading_view).cancel()
    }
}