package com.arttttt.heroeslist.ui.di

import android.content.Context
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.arttttt.dagger2.PerScreen
import com.arttttt.heroeslist.domain.store.HeroesListStoreFactory
import com.arttttt.heroeslist.ui.HeroesListController
import com.arttttt.heroeslist.ui.HeroesListControllerImpl
import com.arttttt.heroeslist.ui.HeroesListCoordinator
import dagger.Module
import dagger.Provides

@Module
internal abstract class HeroesListModule {

    @Module
    companion object {

        @PerScreen
        @Provides
        fun provideStoreFactory(): StoreFactory {
            return DefaultStoreFactory()
        }

        @PerScreen
        @Provides
        fun provideHeroesListController(
            context: Context,
            heroesListStoreFactory: HeroesListStoreFactory,
            coordinator: HeroesListCoordinator
        ): HeroesListController {
            return HeroesListControllerImpl(
                context = context,
                heroesListStore = heroesListStoreFactory.create(),
                coordinator = coordinator
            )
        }

    }

}
