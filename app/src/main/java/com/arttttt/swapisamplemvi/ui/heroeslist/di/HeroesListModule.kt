package com.arttttt.swapisamplemvi.ui.heroeslist.di

import androidx.recyclerview.widget.DiffUtil
import com.arttttt.swapisamplemvi.domain.feature.heroesfeature.HeroesFeature
import com.arttttt.swapisamplemvi.domain.feature.herofeature.HeroFeature
import com.arttttt.swapisamplemvi.ui.base.BaseBindings
import com.arttttt.swapisamplemvi.ui.base.UiActionsDelegate
import com.arttttt.swapisamplemvi.ui.base.recyclerview.DefaultDiffCallback
import com.arttttt.swapisamplemvi.ui.base.recyclerview.IListItem
import com.arttttt.swapisamplemvi.ui.common.ProgressAdapterDelegate
import com.arttttt.swapisamplemvi.ui.heroeslist.HeroesListUiActionsDelegate
import com.arttttt.swapisamplemvi.ui.heroeslist.adapter.delegates.HeroAdapterDelegate
import com.arttttt.swapisamplemvi.ui.heroeslist.adapter.models.HeroAdapterItem
import com.arttttt.swapisamplemvi.ui.heroeslist.HeroesListBindings
import com.arttttt.swapisamplemvi.ui.heroeslist.adapter.delegates.HeroItemListener
import com.arttttt.swapisamplemvi.ui.heroeslist.transfromer.HeroesListTransformer
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import org.koin.dsl.bind
import org.koin.dsl.module

val heroesListModule = module {
    scope<com.arttttt.swapisamplemvi.ui.heroeslist.HeroesListFragment> {
        scoped { HeroFeature() }

        scoped<BaseBindings<*>> {
            HeroesListBindings(
                transformer = HeroesListTransformer(),
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

        scoped<Set<AdapterDelegate<*>>> {
            setOf(
                HeroAdapterDelegate(
                    listener = get()
                ),
                ProgressAdapterDelegate()
            )
        }

        scoped<UiActionsDelegate<*>> {
            HeroesListUiActionsDelegate()
        } bind HeroItemListener::class
    }
}