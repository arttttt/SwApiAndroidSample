package com.arttttt.androidapp

import android.app.Application
import com.arttttt.hero.api.HeroFeatureDependencies
import com.arttttt.hero.impl.HeroComponentHolder
import com.arttttt.heroeslist.api.HeroesListFeatureDependencies
import com.arttttt.heroeslist.impl.HeroesListComponentHolder
import com.arttttt.moduleinjector.dependencyHolder0

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        /**
         * todo: move to common
         */
        HeroesListComponentHolder.dependencyProvider = {
            dependencyHolder0 { holder ->
                object : HeroesListFeatureDependencies {
                    override val dependencyHolder = holder
                }
            }
        }

        HeroComponentHolder.dependencyProvider = {
            dependencyHolder0 { holder ->
                object : HeroFeatureDependencies {
                    override val dependencyHolder = holder
                }
            }
        }
    }
}
