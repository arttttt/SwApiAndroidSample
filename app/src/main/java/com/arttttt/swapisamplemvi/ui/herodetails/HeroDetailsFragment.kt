package com.arttttt.swapisamplemvi.ui.herodetails

import com.arttttt.swapisamplemvi.R
import com.arttttt.swapisamplemvi.ui.RootCoordinator
import com.arttttt.swapisamplemvi.ui.base.AndroidPauseResumeBindings
import com.arttttt.swapisamplemvi.ui.base.BaseFragment
import org.koin.android.ext.android.get
import org.koin.core.qualifier.named

class HeroDetailsFragment: BaseFragment<HeroDetailsViewController>(R.layout.fragment_hero_details) {
    override val binder: AndroidPauseResumeBindings<HeroDetailsViewController> = HeroDetailsBinding(this, get(named<RootCoordinator>()))
    override val viewController = HeroDetailsViewController()

    override fun onBackPressed() {
        viewController.onBackPressed()
    }
}