package com.arttttt.hero.ui

import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.arttttt.hero.domain.store.HeroStore
import com.arttttt.hero.ui.adapter.model.HeroPropertyAdapterItem
import com.arttttt.navigation.BackPressedDispatcher
import com.arttttt.recyclerview.ListItem
import com.arttttt.recyclerview.adapter.models.LoadingAdapterItem
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class HeroControllerImpl @Inject constructor(
    private val heroStore: HeroStore
) : HeroController {

    override fun onViewCreated(view: HeroView, viewLifecycle: Lifecycle, backPressedDispatcher: BackPressedDispatcher) {
        bind(viewLifecycle, BinderLifecycleMode.CREATE_DESTROY) {
            view
                .events
                .filterIsInstance<HeroView.UiEvent.BackPressed>()
                .bindTo { backPressedDispatcher.onBackPressed() }

            heroStore.states.map { state ->
                HeroView.Model(
                    title = state.hero?.name ?: "Loading...",
                    items = if (state.hero != null) {
                        val items = mutableListOf<ListItem>()

                        items += HeroPropertyAdapterItem(
                            title = "Birth year:",
                            property = state.hero.birthYear
                        )

                        items += HeroPropertyAdapterItem(
                            title = "Eye color:",
                            property = state.hero.eyeColor
                        )

                        items += HeroPropertyAdapterItem(
                            title = "Gender:",
                            property = state.hero.gender
                        )

                        items += HeroPropertyAdapterItem(
                            title = "Hair color:",
                            property = state.hero.hairColor
                        )

                        items += HeroPropertyAdapterItem(
                            title = "Height:",
                            property = state.hero.height
                        )

                        items += HeroPropertyAdapterItem(
                            title = "Homeworld:",
                            property = state.hero.homeWorld
                        )

                        items += HeroPropertyAdapterItem(
                            title = "Mass:",
                            property = state.hero.mass
                        )

                        items += HeroPropertyAdapterItem(
                            title = "Skin color:",
                            property = state.hero.skinColor
                        )

                        items
                    } else {
                        listOf(
                            LoadingAdapterItem
                        )
                    }
                )
            } bindTo view
        }
    }

}
