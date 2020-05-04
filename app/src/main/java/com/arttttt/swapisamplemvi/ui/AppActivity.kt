package com.arttttt.swapisamplemvi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.arttttt.swapisamplemvi.R
import com.arttttt.swapisamplemvi.Screens
import com.arttttt.swapisamplemvi.ui.base.IBackHandler
import com.arttttt.swapisamplemvi.utils.extensions.castTo
import org.koin.android.ext.android.inject
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class AppActivity: AppCompatActivity() {

    private val navigator = object: SupportAppNavigator(this, R.id.rootContainer) {}

    private val navHolder: NavigatorHolder by inject()

    private val router: Router by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_activity)

        savedInstanceState ?: router.newRootScreen(Screens.HeroesListScreen())
    }

    override fun onPause() {
        super.onPause()

        navHolder.removeNavigator()
    }

    override fun onResume() {
        navHolder.setNavigator(navigator)

        super.onResume()
    }

    override fun onBackPressed() {
        supportFragmentManager
            .fragments
            .firstOrNull(Fragment::isVisible)
            .castTo<IBackHandler>()
            ?.onBackPressed() ?: super.onBackPressed()
    }
}