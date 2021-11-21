package com.arttttt.hero.domain.store

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.arttttt.hero.domain.repository.HeroRepository
import kotlinx.coroutines.launch

internal class HeroExecutor(
    private val heroRepository: HeroRepository
) : CoroutineExecutor<HeroStore.Intent, HeroStore.Action, HeroStore.State, HeroStore.Message, Nothing>() {

    override fun executeAction(action: HeroStore.Action, getState: () -> HeroStore.State) {
        when (action) {
            is HeroStore.Action.LoadHero -> {
                scope.launch {
                    val hero = heroRepository.loadHero(action.id)

                    dispatch(
                        HeroStore.Message.HeroLoaded(
                            hero = hero
                        )
                    )
                }
            }
        }
    }
}
