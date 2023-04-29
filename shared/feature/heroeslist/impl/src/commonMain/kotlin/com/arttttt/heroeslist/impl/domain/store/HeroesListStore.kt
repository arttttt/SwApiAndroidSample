package com.arttttt.heroeslist.impl.domain.store

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.store.Store
import com.arttttt.heroeslist.api.Hero

internal interface HeroesListStore : Store<HeroesListStore.Intent, HeroesListStore.State, HeroesListStore.Label> {
    
    sealed class Intent {
        object LoadHeroes : Intent()
    }

    @Parcelize
    data class State(
        val currentPage: Int,
        val heroes: List<Hero>,
        val isLoading: Boolean,
        val fullData: Boolean
    ) : Parcelable

    sealed class Message {
        data class HeroesLoaded(val heroes: List<Hero>) : Message()
        object PageIncreased : Message()
        object LoadingStarted : Message()
        object LoadingFinished : Message()
    }

    sealed class Action {
        object LoadHeroes : Action()
    }

    sealed class Label {
        data class Notification(
            val message: String
        ) : Label()
    }
}
