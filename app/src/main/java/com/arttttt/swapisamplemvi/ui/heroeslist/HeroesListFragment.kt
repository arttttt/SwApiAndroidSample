package com.arttttt.swapisamplemvi.ui.heroeslist

import com.arttttt.swapisamplemvi.feature.heroesfeature.HeroesFeature
import com.arttttt.swapisamplemvi.ui.base.BaseFragment
import com.badoo.mvicore.android.AndroidBindings
import org.koin.android.ext.android.get

class HeroesListFragment: BaseFragment<HeroesListView>() {
    override val binder: AndroidBindings<HeroesListView> = HeroesListBinding(this, HeroesFeature(get()))
    override val mviView: HeroesListView = HeroesListView()
}
