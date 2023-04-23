package com.arttttt.app.heroeslist.domain.store

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper

internal class HeroesListBootstrapper(
    private val initialState: HeroesListStore.State
) : CoroutineBootstrapper<HeroesListStore.Action>() {
    override fun invoke() {
        when {
            initialState.isLoading || initialState.heroes.isEmpty() -> dispatch(HeroesListStore.Action.LoadHeroes)
        }
    }
}
