package com.arttttt.heroeslist.ui.list.di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.arttttt.dagger2.PerScreen
import com.arttttt.heroeslist.data.repository.HeroesListRepositoryImpl
import com.arttttt.heroeslist.domain.repository.HeroesListRepository
import com.arttttt.heroeslist.domain.store.HeroesListStore
import com.arttttt.heroeslist.domain.store.HeroesListStoreFactory
import com.arttttt.heroeslist.ui.list.controller.HeroesListController
import com.arttttt.heroeslist.ui.list.controller.HeroesListControllerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class HeroesListModule  {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @PerScreen
        fun provideHeroesListStore(
            factory: HeroesListStoreFactory
        ): HeroesListStore {
            return factory.create()
        }

        @JvmStatic
        @Provides
        @PerScreen
        fun provideStoreFactory(): StoreFactory {
            return DefaultStoreFactory()
        }
    }

    @Binds
    @PerScreen
    abstract fun bindHeroesListController(impl: HeroesListControllerImpl): HeroesListController

    @Binds
    @PerScreen
    abstract fun bindHeroesListRepository(impl: HeroesListRepositoryImpl): HeroesListRepository
}
