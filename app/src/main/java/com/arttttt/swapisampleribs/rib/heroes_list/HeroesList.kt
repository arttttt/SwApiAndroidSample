package com.arttttt.swapisampleribs.rib.heroes_list

import com.arttttt.swapisampleribs.rib.heroes_list.HeroesList.Input
import com.arttttt.swapisampleribs.rib.heroes_list.HeroesList.Output
import com.arttttt.swapisampleribs.rib.heroes_list.view.HeroesListView
import com.arttttt.swapisampleribs.rib.heroes_list.view.HeroesListViewImpl
import com.badoo.ribs.clienthelper.Connectable
import com.badoo.ribs.core.Rib
import com.badoo.ribs.customisation.RibCustomisation
import io.reactivex.functions.Consumer

interface HeroesList : Rib, Connectable<Input, Output> {

    interface Dependency {
        fun output(): Consumer<Output>
    }

    sealed class Input

    sealed class Output {
        object HeroClicked: Output()
    }

    class Customisation(
        val viewFactory: HeroesListView.Factory = HeroesListViewImpl.Factory()
    ) : RibCustomisation
}
