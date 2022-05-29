package com.arttttt.heroeslist.domain.store

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.essenty.statekeeper.consume
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arttttt.dagger2.PerScreen
import com.arttttt.heroeslist.domain.repository.HeroesListRepository
import javax.inject.Inject

@PerScreen
internal class HeroesListStoreFactory @Inject constructor(
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
                currentPage = 1,
                isLoading = false,
                fullData = false
            )
    
            object : HeroesListStore,
                            Store<HeroesListStore.Intent, HeroesListStore.State, HeroesListStore.Label> by storeFactory.create(
                                name = null,
                                initialState = initialState,
                                bootstrapper = HeroesListBootstrapper(
                                    initialState = initialState
                                ),
                                executorFactory = {
                                    HeroesListExecutorImpl(
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
