package com.arttttt.heroeslist.ui.list

import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.alphicc.brick.Screen
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.lifecycle.essentyLifecycle
import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arttttt.heroeslist.ui.list.di.DaggerHeroesListComponent
import com.arttttt.heroeslist.ui.list.di.HeroesListComponent
import com.arttttt.heroeslist.ui.list.di.HeroesListDependencies
import com.arttttt.heroeslist.ui.list.view.HeroesListView
import com.arttttt.heroeslist.ui.list.view.HeroesListViewImpl

internal fun HeroesListScreen(
    dependencies: HeroesListDependencies,
) = Screen(
    key = "heroes_list_screen",
    onCreate = { _, argument ->
        val (stateKeeper, instanceKeeper) = argument.get<Pair<StateKeeper, InstanceKeeper>>()

        DaggerHeroesListComponent
            .factory()
            .create(
                dependencies = dependencies,
                stateKeeper = stateKeeper,
                instanceKeeper = instanceKeeper,
            )
    },
    content = { argument ->
        val component = remember {
            argument.get<HeroesListComponent>()
        }

        val view: HeroesListView = remember {
            HeroesListViewImpl()
        }

        view.Content()

        component.controller.onViewCreated(
            view = view,
            lifecycle = LocalLifecycleOwner.current.essentyLifecycle()
        )
    }
)
