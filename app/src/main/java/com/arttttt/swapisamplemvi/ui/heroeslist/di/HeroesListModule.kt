package com.arttttt.swapisamplemvi.ui.heroeslist.di

import androidx.recyclerview.widget.DiffUtil
import com.arttttt.swapisamplemvi.di.PerFragment
import com.arttttt.swapisamplemvi.feature.heroesfeature.HeroesFeature
import com.arttttt.swapisamplemvi.feature.herofeature.HeroFeature
import com.arttttt.swapisamplemvi.ui.RootCoordinator
import com.arttttt.swapisamplemvi.ui.base.BaseBindings
import com.arttttt.swapisamplemvi.ui.base.BaseFragment
import com.arttttt.swapisamplemvi.ui.base.recyclerview.DefaultDiffCallback
import com.arttttt.swapisamplemvi.ui.base.recyclerview.IListItem
import com.arttttt.swapisamplemvi.ui.common.ProgressAdapterDelegate
import com.arttttt.swapisamplemvi.ui.heroeslist.HeroesListFragment
import com.arttttt.swapisamplemvi.ui.heroeslist.HeroesListViewModel
import com.arttttt.swapisamplemvi.ui.heroeslist.adapter.HeroAdapterDelegate
import com.arttttt.swapisamplemvi.ui.heroeslist.adapter.HeroAdapterItem
import com.arttttt.swapisamplemvi.ui.heroeslist.bindings.HeroesListBindings
import com.arttttt.swapisamplemvi.ui.heroeslist.transfromer.HeroesListFragmentEventsTransformer
import com.arttttt.swapisamplemvi.utils.extensions.unsafeCastTo
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
class HeroesListModule {

    @PerFragment
    @Provides
    fun provideHeroesListBindings(
        heroesFeature: HeroesFeature,
        heroFeature: HeroFeature,
        coordinator: RootCoordinator,
        transformer: HeroesListFragmentEventsTransformer
    ): BaseBindings<BaseFragment<HeroesListFragment.HeroesListUiAction, HeroesListViewModel>> {
        return HeroesListBindings(
            heroesFeature = heroesFeature,
            heroFeature = heroFeature,
            coordinator = coordinator,
            heroesListFragmentEventsTransformer = transformer
        ).unsafeCastTo()
    }

    @PerFragment
    @Provides
    @IntoSet
    fun provideHeroDelegate(fragment: HeroesListFragment): AdapterDelegate<List<IListItem>> {
        return HeroAdapterDelegate(fragment)
    }

    @PerFragment
    @Provides
    @IntoSet
    fun provideProgressDelegate(): AdapterDelegate<List<IListItem>> {
        return ProgressAdapterDelegate()
    }

    @PerFragment
    @Provides
    fun provideDiffCallback(): DiffUtil.ItemCallback<IListItem> {
        return object: DefaultDiffCallback() {
            override fun areItemsTheSame(oldItem: IListItem, newItem: IListItem): Boolean {
                return if (oldItem is HeroAdapterItem && newItem is HeroAdapterItem) {
                    oldItem.name == newItem.name
                } else {
                    super.areItemsTheSame(oldItem, newItem)
                }
            }
        }
    }
}