package com.arttttt.swapisamplemvi.ui.herodetails

import com.arttttt.swapisamplemvi.R
import com.arttttt.swapisamplemvi.ui.RootCoordinator
import com.arttttt.swapisamplemvi.ui.base.BaseFragment
import com.arttttt.swapisamplemvi.ui.base.UiAction
import com.arttttt.swapisamplemvi.ui.heroeslist.HeroesListFragment
import com.arttttt.swapisamplemvi.utils.extensions.unsafeCastTo
import com.badoo.mvicore.android.AndroidBindings
import io.reactivex.Observable
import org.koin.android.ext.android.get
import org.koin.core.qualifier.named

class HeroDetailsFragment: BaseFragment<HeroDetailsFragment.HeroDetailsUiAction, HeroDetailsViewModel>(R.layout.fragment_hero_details) {
    sealed class HeroDetailsUiAction: UiAction {
        object BackPressed: HeroDetailsUiAction()
    }

    override val binder: AndroidBindings<BaseFragment<HeroDetailsUiAction, HeroDetailsViewModel>> = HeroDetailsBinding(
        lifecycleOwner = this,
        rootCoordinator = get(named<RootCoordinator>())
    ).unsafeCastTo()

    override fun onBackPressed() {
        Observable
            .just(HeroDetailsUiAction.BackPressed)
            .emitUiAction()
    }
}
