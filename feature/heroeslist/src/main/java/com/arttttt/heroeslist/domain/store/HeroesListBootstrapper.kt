package com.arttttt.heroeslist.domain.store

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import kotlinx.coroutines.launch

internal class HeroesListBootstrapper(
    private val initialState: HeroesListStore.State
) : CoroutineBootstrapper<HeroesListStore.Action>() {
    override fun invoke() {
        scope.launch {
            when {
                initialState.isLoading ||
                initialState.heroes.isEmpty() -> dispatch(HeroesListStore.Action.LoadHeroes)
            }
        }
    }
}
