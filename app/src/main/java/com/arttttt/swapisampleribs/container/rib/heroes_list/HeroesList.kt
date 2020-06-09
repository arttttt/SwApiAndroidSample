package com.arttttt.swapisampleribs.container.rib.heroes_list

import com.arttttt.swapisampleribs.container.rib.heroes_list.HeroesList.Input
import com.arttttt.swapisampleribs.container.rib.heroes_list.HeroesList.Output
import com.badoo.ribs.clienthelper.Connectable
import com.badoo.ribs.core.Rib
import com.badoo.ribs.customisation.RibCustomisation

interface HeroesList : Rib, Connectable<Input, Output> {

    interface Dependency

    sealed class Input

    sealed class Output

    class Customisation(
        val viewFactory: heroesListView.Factory = HeroesListViewImpl.Factory()
    ) : RibCustomisation
}
