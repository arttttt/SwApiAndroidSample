package com.arttttt.hero.ui.di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.arttttt.dagger2.PerScreen
import com.arttttt.hero.domain.store.HeroStoreFactory
import com.arttttt.hero.ui.HeroController
import com.arttttt.hero.ui.HeroControllerImpl
import dagger.Module
import dagger.Provides

@Module
internal abstract class HeroModule {

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
            heroStoreFactory: HeroStoreFactory,
            @HeroId heroId: Int
        ): HeroController {
            return HeroControllerImpl(
                heroStore = heroStoreFactory.create(
                    heroId = heroId
                )
            )
        }

    }

}
