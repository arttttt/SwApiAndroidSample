package com.arttttt.app.heroeslist.domain.store

import com.arkivanov.mvikotlin.core.store.Reducer

internal object HeroesListReducer : Reducer<HeroesListStore.State, HeroesListStore.Message> {
    override fun HeroesListStore.State.reduce(msg: HeroesListStore.Message): HeroesListStore.State {
        return when (msg) {
            is HeroesListStore.Message.HeroesLoaded -> copy(
                heroes = heroes + msg.heroes,
                fullData = msg.heroes.size < 10
            )
            is HeroesListStore.Message.PageIncreased -> copy(
                currentPage = currentPage + 1,
            )
            is HeroesListStore.Message.LoadingStarted -> copy(
                isLoading = true,
            )
            is HeroesListStore.Message.LoadingFinished -> copy(
                isLoading = false,
            )
        }
    }
}
