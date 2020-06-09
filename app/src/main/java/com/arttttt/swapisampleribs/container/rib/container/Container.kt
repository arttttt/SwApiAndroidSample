package com.arttttt.swapisampleribs.container.rib.container

import com.arttttt.swapisampleribs.container.rib.container.Container.Input
import com.arttttt.swapisampleribs.container.rib.container.Container.Output
import com.badoo.ribs.clienthelper.Connectable
import com.badoo.ribs.core.Rib

interface Container : Rib, Connectable<Input, Output> {

    interface Dependency

    sealed class Input

    sealed class Output
}
