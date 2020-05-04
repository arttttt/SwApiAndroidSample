package com.arttttt.swapisamplemvi.ui.heroeslist.di

import androidx.recyclerview.widget.DiffUtil
import com.arttttt.swapisamplemvi.domain.feature.heroesfeature.HeroesFeature
import com.arttttt.swapisamplemvi.domain.feature.herofeature.HeroFeature
import com.arttttt.swapisamplemvi.ui.base.BaseBindings
import com.arttttt.swapisamplemvi.ui.base.recyclerview.DefaultDiffCallback
import com.arttttt.swapisamplemvi.ui.base.recyclerview.IListItem
import com.arttttt.swapisamplemvi.ui.common.ProgressAdapterDelegate
import com.arttttt.swapisamplemvi.ui.heroeslist.HeroesListFragment
import com.arttttt.swapisamplemvi.ui.heroeslist.adapter.HeroAdapterDelegate
import com.arttttt.swapisamplemvi.ui.heroeslist.adapter.HeroAdapterItem
import com.arttttt.swapisamplemvi.ui.heroeslist.adapter.HeroItemListener
import com.arttttt.swapisamplemvi.ui.heroeslist.bindings.HeroesListBindings
import com.arttttt.swapisamplemvi.ui.heroeslist.transfromer.HeroesListFragmentEventsTransformer
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import io.reactivex.functions.Consumer
import org.koin.dsl.module
import timber.log.Timber

val heroesListModule = module {
    scope<HeroesListFragment> {
        scoped { HeroFeature() }

        scoped<BaseBindings<*>> {
            HeroesListBindings(
                heroesListFragmentEventsTransformer = HeroesListFragmentEventsTransformer(),
                heroFeature = get(),
                coordinator = get(),
                heroesFeature = HeroesFeature(
                    swRepository = get()
                )
            )
        }

        scoped<DiffUtil.ItemCallback<IListItem>> {
            object: DefaultDiffCallback() {
                override fun areItemsTheSame(oldItem: IListItem, newItem: IListItem): Boolean {
                    return if (oldItem is HeroAdapterItem && newItem is HeroAdapterItem) {
                        oldItem.name == newItem.name
                    } else {
                        super.areItemsTheSame(oldItem, newItem)
                    }
                }
            }
        }

        scoped<Set<AdapterDelegate<*>>> { (listener: HeroesListFragment) ->
            setOf(
                HeroAdapterDelegate(listener),
                ProgressAdapterDelegate()
            )
        }
    }
}