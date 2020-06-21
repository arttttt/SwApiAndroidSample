package com.arttttt.swapisampleribs.rib.heroes_list.builder

import com.arttttt.swapisampleribs.rib.heroes_list.HeroesList
import com.arttttt.swapisampleribs.rib.heroes_list.HeroesListInteractor
import com.arttttt.swapisampleribs.rib.heroes_list.feature.HeroesListFeature
import com.badoo.ribs.core.builder.BuildParams
import org.koin.dsl.module

val heroesListModule = module {
    scope<HeroesList>() {
        scoped {
            HeroesListFeature(
                heroesRepository = get()
            )
        }

        scoped { (buildParams: BuildParams<*>) ->
            HeroesListInteractor(
                buildParams = buildParams,
                feature = get(),
                output = get<HeroesList.Dependency>().output()
            )
        }

        scoped { (buildParams: BuildParams<*>) ->
            buildParams.getOrDefault(HeroesList.Customisation())
        }
    }
}