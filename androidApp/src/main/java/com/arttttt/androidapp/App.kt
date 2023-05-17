package com.arttttt.androidapp

import android.app.Application
import com.arttttt.hero.api.HeroFeatureDependencies
import com.arttttt.hero.api.HeroView
import com.arttttt.hero.impl.HeroComponentHolder
import com.arttttt.hero.impl.ui.HeroViewImpl
import com.arttttt.heroeslist.api.HeroesListFeatureDependencies
import com.arttttt.heroeslist.api.components.HeroesListComponent
import com.arttttt.heroeslist.api.ui.HeroesListToolbarView
import com.arttttt.heroeslist.api.ui.list.HeroesListView
import com.arttttt.heroeslist.impl.HeroesListComponentHolder
import com.arttttt.heroeslist.impl.ui.list.HeroesListViewImpl
import com.arttttt.heroeslist.impl.ui.toolbar.HeroesListToolbarViewImpl

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        HeroesListComponentHolder.dependencyProvider = {
            object : HeroesListFeatureDependencies {
                override val listViewFactory: (HeroesListComponent) -> HeroesListView = { component ->
                        HeroesListViewImpl(component)
                    }

                override val toolbarViewFactory: () -> HeroesListToolbarView = {
                    HeroesListToolbarViewImpl()
                }
            }
        }

        HeroComponentHolder.dependencyProvider = {
            object : HeroFeatureDependencies {
                override val heroViewFactory: () -> HeroView = {
                    HeroViewImpl()
                }
            }
        }
    }
}
