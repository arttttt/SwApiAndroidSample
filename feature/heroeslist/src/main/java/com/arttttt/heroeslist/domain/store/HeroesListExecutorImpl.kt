package com.arttttt.heroeslist.domain.store

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.arttttt.heroeslist.domain.repository.HeroesListRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

internal class HeroesListExecutorImpl(
    private val heroesListRepository: HeroesListRepository
) : CoroutineExecutor<HeroesListStore.Intent, HeroesListStore.Action, HeroesListStore.State, HeroesListStore.Message, HeroesListStore.Label>() {

    private val exceptionsHandler = CoroutineExceptionHandler { _, error ->
        publish(
            HeroesListStore.Label.Notification(
                message = error.message ?: "Empty message"
            )
        )
    }

    override fun executeIntent(
        intent: HeroesListStore.Intent,
        getState: () -> HeroesListStore.State
    ) {
        when (intent) {
            is HeroesListStore.Intent.LoadHeroes -> {
                loadHeroes(
                    page = getState.invoke().currentPage
                )
            }
            is HeroesListStore.Intent.LoadMore -> {
                getState.invoke().also { state ->
                    if (state.isLoading || state.fullData) return
                }

                dispatch(HeroesListStore.Message.PageIncreased)

                loadHeroes(
                    page = getState.invoke().currentPage
                )
            }
        }
    }

    override fun executeAction(
        action: HeroesListStore.Action,
        getState: () -> HeroesListStore.State
    ) {
        when (action) {
            is HeroesListStore.Action.LoadHeroes -> {
                loadHeroes(
                    page = getState.invoke().currentPage
                )
            }
        }
    }

    private fun loadHeroes(page: Int) {
        dispatch(HeroesListStore.Message.Loading)

        scope.launch {
            publish(
                HeroesListStore.Label.Notification(
                    message = "before loading"
                )
            )

            /*val heroes = safeWithContext(
                coroutineContext = Dispatchers.IO,
                defaultValue = emptyList()
            ) {
                heroesListRepository.loadHeroes(
                    page = page
                )
            }*/

            val heroes = supervisorScope {
                async {
                    heroesListRepository.loadHeroes(page)
                }
            }

            publish(
                HeroesListStore.Label.Notification(
                    message = "after loading"
                )
            )

            dispatch(
                HeroesListStore.Message.HeroesLoaded(
                    heroes = heroes.safeAwait().getOrElse {
                        emptyList()
                    }
                )
            )
        }
    }

    private suspend fun <T> safeWithContext(
        coroutineContext: CoroutineContext,
        defaultValue: T,
        block: suspend CoroutineScope.() -> T
    ) : T {
        return try {
            withContext(coroutineContext, block)
        } catch (e: Exception) {
            defaultValue
        }
    }

    private suspend fun <T> Deferred<T>.safeAwait(): Result<T> {
        return try {
            Result.success(await())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
