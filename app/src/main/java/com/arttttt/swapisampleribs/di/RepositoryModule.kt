package com.arttttt.swapisampleribs.di

import com.arttttt.swapisampleribs.data.repository.HeroesRepositoryImpl
import com.arttttt.swapisampleribs.domain.repository.HeroesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<HeroesRepository> {
        HeroesRepositoryImpl(
            swApi = get()
        )
    }
}