package com.arttttt.swapisamplemvi.ui.heroeslist

import com.arttttt.swapisamplemvi.ui.base.UiActionsDelegate
import com.arttttt.swapisamplemvi.ui.heroeslist.adapter.delegates.HeroItemListener
import io.reactivex.functions.Consumer

class HeroesListUiActionsDelegate: UiActionsDelegate<HeroesListFragment.Action>(),
    HeroItemListener {
    override val clicks = Consumer<Int> { position ->
        uiActions.accept(HeroesListFragment.Action.HeroClicked(position))
    }
}