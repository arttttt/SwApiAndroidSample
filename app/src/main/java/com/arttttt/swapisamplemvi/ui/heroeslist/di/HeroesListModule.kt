package com.arttttt.swapisamplemvi.ui.heroeslist.di

import com.arttttt.swapisamplemvi.domain.feature.heroesfeature.HeroesFeature
import com.arttttt.swapisamplemvi.domain.repository.SwRepository
import com.arttttt.swapisamplemvi.ui.base.recyclerview.IListItem
import com.arttttt.swapisamplemvi.ui.common.ProgressAdapterDelegate
import com.arttttt.swapisamplemvi.ui.heroeslist.HeroesListUiActionsDelegate
import com.arttttt.swapisamplemvi.ui.heroeslist.adapter.delegates.HeroAdapterDelegate
import com.arttttt.swapisamplemvi.ui.heroeslist.adapter.delegates.HeroItemListener
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        HeroesListModule.Abstract::class
    ]
)
object HeroesListModule {

    @Module
    interface Abstract {
        @Binds
        @HeroesListScope
        fun provideHeroItemListener(impl: HeroesListUiActionsDelegate): HeroItemListener
    }

    @JvmStatic
    @Provides
    @HeroesListScope
    fun provideAdapterDelegates(listener: HeroItemListener): Set<AdapterDelegate<List<IListItem>>> {
        return setOf(
            ProgressAdapterDelegate(),
            HeroAdapterDelegate(listener)
        )
    }

    @JvmStatic
    @Provides
    @HeroesListScope
    fun provideHeroesFeature(swRepository: SwRepository): HeroesFeature {
        return HeroesFeature(
            swRepository = swRepository
        )
    }
}