package com.arttttt.swapisamplemvi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.arttttt.swapisamplemvi.App
import com.arttttt.swapisamplemvi.R
import com.arttttt.swapisamplemvi.Screens
import com.arttttt.swapisamplemvi.di.components.DaggerUiComponent
import com.arttttt.swapisamplemvi.di.components.UiComponent
import com.arttttt.swapisamplemvi.ui.base.IBackHandler
import com.arttttt.swapisamplemvi.utils.extensions.castTo
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class AppActivity: AppCompatActivity() {

    private val navigator = object: SupportAppNavigator(this, R.id.rootContainer) {}

    @Inject
    lateinit var navHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    lateinit var component: UiComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        component = DaggerUiComponent.factory().create((applicationContext as App).appComponent).apply {
            inject(this@AppActivity)
        }
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