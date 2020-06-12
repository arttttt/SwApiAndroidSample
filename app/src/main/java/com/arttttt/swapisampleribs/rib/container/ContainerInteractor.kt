package com.arttttt.swapisampleribs.rib.container

import com.badoo.ribs.core.BackStackInteractor
import com.badoo.ribs.core.builder.BuildParams

class ContainerInteractor(
    buildParams: BuildParams<Nothing?>
): BackStackInteractor<Container, Nothing, ContainerRouter.Configuration>(
    buildParams = buildParams,
    initialConfiguration = ContainerRouter.Configuration.Main
)