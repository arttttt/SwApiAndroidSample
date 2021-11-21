package com.arttttt.heroeslist.ui.platform

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.arkivanov.essenty.instancekeeper.instanceKeeper
import com.arkivanov.essenty.lifecycle.asEssentyLifecycle
import com.arkivanov.essenty.statekeeper.stateKeeper
import com.arttttt.heroeslist.HeroesListComponentHolder
import com.arttttt.heroeslist.R
import com.arttttt.heroeslist.ui.HeroesListController
import com.arttttt.heroeslist.ui.HeroesListViewImpl
import com.arttttt.heroeslist.ui.di.DaggerHeroesListComponent
import javax.inject.Inject

class HeroesListFragment : Fragment(R.layout.fragment_heroes_list) {

    @Inject
    internal lateinit var controller: HeroesListController

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerHeroesListComponent
            .factory()
            .create(
                dependencies = HeroesListComponentHolder.getComponent(),
                stateKeeper = stateKeeper(),
                instanceKeeper = instanceKeeper()
            )
            .inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        controller.onViewCreated(
            view = HeroesListViewImpl(view),
            viewLifecycle = viewLifecycleOwner.lifecycle.asEssentyLifecycle(),
        )
    }

}
