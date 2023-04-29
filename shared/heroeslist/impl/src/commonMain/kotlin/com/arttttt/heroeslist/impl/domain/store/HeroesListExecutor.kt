package com.arttttt.heroeslist.impl.domain.store

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.arttttt.heroeslist.impl.domain.repository.HeroesListRepository
import com.arttttt.heroeslist.impl.exceptCancellationException
import com.arttttt.heroeslist.impl.finally
import kotlinx.coroutines.*

internal class HeroesListExecutor(
    private val heroesListRepository: HeroesListRepository
) : CoroutineExecutor<HeroesListStore.Intent, HeroesListStore.Action, HeroesListStore.State, HeroesListStore.Message, HeroesListStore.Label>() {

    override fun executeAction(
        action: HeroesListStore.Action,
        getState: () -> HeroesListStore.State
    ) {
        when (action) {
            is HeroesListStore.Action.LoadHeroes -> loadHeroes(getState)
        }
    }

    override fun executeIntent(
        intent: HeroesListStore.Intent,
        getState: () -> HeroesListStore.State
    ) {
        when (intent) {
            is HeroesListStore.Intent.LoadHeroes -> loadHeroes(getState)
        }
    }

    private fun loadHeroes(getState: () -> HeroesListStore.State) {
        if (getState.invoke().isLoading || getState.invoke().fullData) return

        scope.launch {
            dispatch(HeroesListStore.Message.LoadingStarted)

            publish(
                HeroesListStore.Label.Notification(
                    message = "before loading"
                )
            )

            dispatch(HeroesListStore.Message.PageIncreased)

            val heroes = runCatching {
                    withContext(Dispatchers.IO) {
                        heroesListRepository.loadHeroes(getState.invoke().currentPage)
                    }
                }
                .exceptCancellationException()
                .finally {
                    dispatch(HeroesListStore.Message.LoadingFinished)
                }
                .getOrElse {
                    /**
                     * todo: proper error handling
                     */

                    emptyList()
                }

            dispatch(
                HeroesListStore.Message.HeroesLoaded(
                    heroes = heroes,
                )
            )

            publish(
                HeroesListStore.Label.Notification(
                    message = "after loading"
                )
            )
        }
    }
}
