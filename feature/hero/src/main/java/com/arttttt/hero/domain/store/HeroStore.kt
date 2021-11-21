package com.arttttt.hero.domain.store

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.store.Store
import com.arttttt.hero.domain.entity.Hero

internal interface HeroStore : Store<HeroStore.Intent, HeroStore.State, Nothing> {

    sealed class Intent

    @Parcelize
    data class State(
        val hero: Hero?
    ) : Parcelable

    sealed class Message {
        data class HeroLoaded(val hero: Hero) : Message()
    }

    sealed class Action {
        data class LoadHero(val id: Int) : Action()
    }

}
