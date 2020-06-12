package com.arttttt.swapisampleribs.rib.container

import com.arttttt.swapisampleribs.rib.container.Container.Input
import com.arttttt.swapisampleribs.rib.container.Container.Output
import com.badoo.ribs.clienthelper.Connectable
import com.badoo.ribs.core.Rib

interface Container : Rib, Connectable<Input, Output> {

    interface Dependency

    sealed class Input

    sealed class Output
}
