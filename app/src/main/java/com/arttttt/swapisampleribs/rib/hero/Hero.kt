package com.arttttt.swapisampleribs.rib.hero

import com.badoo.ribs.clienthelper.Connectable
import com.badoo.ribs.core.Rib
import com.badoo.ribs.customisation.RibCustomisation
import com.arttttt.swapisampleribs.rib.hero.Hero.Input
import com.arttttt.swapisampleribs.rib.hero.Hero.Output

interface Hero : Rib, Connectable<Input, Output> {

    interface Dependency

    sealed class Input

    sealed class Output

    class Customisation(
        val viewFactory: HeroView.Factory = HeroViewImpl.Factory()
    ) : RibCustomisation
}
