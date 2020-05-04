package com.arttttt.swapisamplemvi.di

import com.arttttt.swapisamplemvi.data.database.HeroesDatabase
import org.koin.dsl.module

val daoModule = module {
    single { get<HeroesDatabase>().getHeroesDao() }
}