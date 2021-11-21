package com.arttttt.hero.domain.store

import com.arkivanov.mvikotlin.core.store.Reducer

internal object HeroReducer : Reducer<HeroStore.State, HeroStore.Message> {

    override fun HeroStore.State.reduce(msg: HeroStore.Message): HeroStore.State {
        return when (msg) {
            is HeroStore.Message.HeroLoaded -> copy(
                hero = msg.hero
            )
        }
    }
}
