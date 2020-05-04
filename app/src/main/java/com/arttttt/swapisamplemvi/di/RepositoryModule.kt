package com.arttttt.swapisamplemvi.di

import com.arttttt.swapisamplemvi.data.repository.DefaultSwRepository
import com.arttttt.swapisamplemvi.domain.repository.SwRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<SwRepository> {
        DefaultSwRepository(
            heroesDao = get(),
            swApi = get()
        )
    }
}