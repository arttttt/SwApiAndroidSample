package com.arttttt.hero.ui.platform

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.arkivanov.essenty.instancekeeper.instanceKeeper
import com.arkivanov.essenty.lifecycle.asEssentyLifecycle
import com.arkivanov.essenty.statekeeper.stateKeeper
import com.arttttt.hero.HeroComponentHolder
import com.arttttt.hero.R
import com.arttttt.hero.ui.HeroController
import com.arttttt.hero.ui.HeroViewImpl
import com.arttttt.hero.ui.di.DaggerHeroComponent
import javax.inject.Inject

class HeroFragment : Fragment(R.layout.fragment_hero) {

    companion object {
        internal const val HERO_ID = "hero_id"
    }

    @Inject
    internal lateinit var controller: HeroController

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerHeroComponent
            .factory()
            .create(
                dependencies = HeroComponentHolder.getComponent(),
                stateKeeper = stateKeeper(),
                heroId = requireArguments().getInt(HERO_ID),
                instanceKeeper = instanceKeeper()
            )
            .inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        controller.onViewCreated(
            view = HeroViewImpl(
                root = view
            ),
            viewLifecycle = viewLifecycleOwner.lifecycle.asEssentyLifecycle(),
            backPressedDispatcher = requireActivity().onBackPressedDispatcher::onBackPressed
        )
    }

}
