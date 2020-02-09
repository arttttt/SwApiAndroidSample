package com.arttttt.swapisamplemvi

import androidx.fragment.app.Fragment
import com.arttttt.swapisamplemvi.ui.herodetails.HeroDetailsFragment
import com.arttttt.swapisamplemvi.ui.heroeslist.HeroesListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {
    class HeroesListScreen: SupportAppScreen() {
        override fun getFragment(): Fragment {
            return HeroesListFragment()
        }
    }

    class HeroDetailsScreen: SupportAppScreen() {
        override fun getFragment(): Fragment {
            return HeroDetailsFragment()
        }
    }
}