package com.arttttt.swapisamplemvi.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.arttttt.swapisamplemvi.R
import com.arttttt.swapisamplemvi.Screens
import com.arttttt.swapisamplemvi.ui.base.IBackHandler
import com.arttttt.swapisamplemvi.ui.base.SharedElementProvider
import com.arttttt.swapisamplemvi.utils.extensions.castTo
import com.arttttt.swapisamplemvi.utils.extensions.isNull
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import javax.inject.Inject

class AppActivity: AppCompatActivity(), HasAndroidInjector {

    @Inject
    protected lateinit var injector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return injector
    }

    private val navigator = object: SupportAppNavigator(this, R.id.rootContainer) {
        override fun setupFragmentTransaction(
            command: Command,
            currentFragment: Fragment?,
            nextFragment: Fragment?,
            fragmentTransaction: FragmentTransaction
        ) {
            currentFragment ?: return

            fragmentTransaction.setCustomAnimations(
                R.anim.slide_in,
                R.anim.slide_out,
                R.anim.pop_slide_in,
                R.anim.pop_slide_out
            )
            fragmentTransaction.setReorderingAllowed(true)
            if (command !is Forward) return
            if (currentFragment !is SharedElementProvider) return

            val view: View = currentFragment.provideSharedElement()
            fragmentTransaction.addSharedElement(view, view.transitionName)
        }
    }

    @Inject
    protected lateinit var navHolder: NavigatorHolder

    @Inject
    protected lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_activity)

        if (savedInstanceState.isNull()) {
            router.newRootScreen(Screens.HeroesListScreen())
        }
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