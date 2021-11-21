package com.arttttt.hero.domain.store

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.essenty.statekeeper.consume
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arttttt.dagger2.PerScreen
import com.arttttt.hero.domain.repository.HeroRepository
import com.arttttt.stateKey
import javax.inject.Inject

@PerScreen
internal class HeroStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val stateKeeper: StateKeeper,
    private val heroRepository: HeroRepository,
    private val instanceKeeper: InstanceKeeper
) {

    fun create(heroId: Int): HeroStore {
        val stateKey = stateKey<HeroStore>()
        
        val store = instanceKeeper.getStore {
    
            val initialState = stateKeeper.consume(stateKey) ?: HeroStore.State(
                hero = null
            )
    
            object : HeroStore,
                            Store<HeroStore.Intent, HeroStore.State, Nothing> by storeFactory.create(
                                initialState = initialState,
                                bootstrapper = SimpleBootstrapper(
                                    HeroStore.Action.LoadHero(
                                        id = heroId
                                    )
                                ),
                                executorFactory = {
                                    HeroExecutor(
                                        heroRepository = heroRepository
                                    )
                                },
                                reducer = HeroReducer
                            ) {}
        }
    
        stateKeeper.register(stateKey) {
            store.state
        }
        
        return store
    }

}
