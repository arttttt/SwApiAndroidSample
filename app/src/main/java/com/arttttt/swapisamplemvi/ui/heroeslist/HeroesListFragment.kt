package com.arttttt.swapisamplemvi.ui.heroeslist

import com.arttttt.swapisamplemvi.R
import com.arttttt.swapisamplemvi.feature.heroesfeature.HeroesFeature
import com.arttttt.swapisamplemvi.ui.RootCoordinator
import com.arttttt.swapisamplemvi.ui.base.AndroidPauseResumeBindings
import com.arttttt.swapisamplemvi.ui.base.BaseFragment
import org.koin.android.ext.android.get
import org.koin.core.qualifier.named

class HeroesListFragment: BaseFragment<HeroesListViewController>(R.layout.fragment_list) {

    override val binder: AndroidPauseResumeBindings<HeroesListViewController> = HeroesListBinding(
        lifecycleOwner = this,
        coordinator = get(named<RootCoordinator>()),
        heroesFeature = HeroesFeature(
            swRepository = get(),
            timeCapsule = timeCapsule
        )
    )
    override val viewController: HeroesListViewController = HeroesListViewController()


    override fun onBackPressed() {
        viewController.onBackPressed()
    }
}
