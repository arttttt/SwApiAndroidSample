package com.arttttt.heroeslist.impl.domain.store

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.essenty.statekeeper.consume
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arttttt.heroeslist.impl.domain.repository.HeroesListRepository

internal class HeroesListStoreFactory(
    private val storeFactory: StoreFactory,
    private val heroesListRepository: HeroesListRepository,
    private val stateKeeper: StateKeeper,
    private val instanceKeeper: InstanceKeeper
) {
    fun create(): HeroesListStore {
        val stateKey = HeroesListStore::class.java.name
        
        val store = instanceKeeper.getStore {
            
            val initialState = stateKeeper.consume(stateKey) ?: HeroesListStore.State(
                heroes = emptyList(),
                currentPage = 0,
                isLoading = false,
                fullData = false
            )
    
            object : HeroesListStore,
                            Store<HeroesListStore.Intent, HeroesListStore.State, HeroesListStore.Label> by storeFactory.create(
                                name = stateKey,
                                initialState = initialState,
                                bootstrapper = HeroesListBootstrapper(
                                    initialState = initialState
                                ),
                                executorFactory = {
                                    HeroesListExecutor(
                                        heroesListRepository = heroesListRepository
                                    )
                                },
                                reducer = HeroesListReducer
                            ) {}
        }
    
        stateKeeper.register(stateKey) {
            store.state
        }
        
        return store
    }
}
