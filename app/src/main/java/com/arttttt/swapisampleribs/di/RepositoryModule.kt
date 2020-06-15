package com.arttttt.swapisampleribs.di

import com.arttttt.swapisampleribs.data.repository.HeroesRepositoryImpl
import com.arttttt.swapisampleribs.data.repository.MoviesRepositoryImpl
import com.arttttt.swapisampleribs.domain.repository.HeroesRepository
import com.arttttt.swapisampleribs.domain.repository.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<HeroesRepository> {
        HeroesRepositoryImpl(
            swApi = get()
        )
    }

    single<MoviesRepository> {
        MoviesRepositoryImpl(
            swApi = get()
        )
    }
}