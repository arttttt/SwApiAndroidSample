package com.arttttt.swapisamplemvi.di

import androidx.recyclerview.widget.DiffUtil
import com.arttttt.swapisamplemvi.ui.RootCoordinator
import com.arttttt.swapisamplemvi.ui.base.navigation.Coordinator
import com.arttttt.swapisamplemvi.ui.base.recyclerview.IListItem
import com.arttttt.swapisamplemvi.ui.base.recyclerview.ListDifferAdapter
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import org.koin.dsl.module
import ru.terrakok.cicerone.Cicerone

val uiModule = module {
    val cicerone = Cicerone.create()

    single { cicerone.router }
    single { cicerone.navigatorHolder }
    single { RootCoordinator(cicerone.router) }

    factory { (diffCallback: DiffUtil.ItemCallback< IListItem>, delegates: Set<AdapterDelegate<List<IListItem>>>) ->
        ListDifferAdapter(
            diffCallback = diffCallback,
            delegates = delegates
        )
    }
}