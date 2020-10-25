package com.arttttt.swapisamplemvi.ui.heroeslist

import com.arttttt.swapisamplemvi.ui.base.UiActionsDelegate
import com.arttttt.swapisamplemvi.ui.heroeslist.adapter.delegates.HeroItemListener
import com.arttttt.swapisamplemvi.ui.heroeslist.di.HeroesListScope
import io.reactivex.functions.Consumer
import javax.inject.Inject

@HeroesListScope
class HeroesListUiActionsDelegate @Inject constructor(): UiActionsDelegate<HeroesListFragment.Action>(),
    HeroItemListener {
    override val clicks = Consumer<Int> { position ->
        uiActions.accept(HeroesListFragment.Action.HeroClicked(position))
    }
}