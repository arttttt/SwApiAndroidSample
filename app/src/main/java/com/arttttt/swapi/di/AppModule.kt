package com.arttttt.swapi.di

import com.arttttt.swapi.model.hero.local.HeroDatabase
import com.arttttt.swapi.model.hero.remote.HeroApi
import com.arttttt.swapi.model.hero.repository.HeroRepository
import com.arttttt.swapi.model.hero.repository.HeroRepositoryImpl
import com.arttttt.swapi.presenter.herodetails.HeroDetailsContract
import com.arttttt.swapi.presenter.herodetails.HeroDetailsPresenter
import com.arttttt.swapi.presenter.heroes.HeroesContract
import com.arttttt.swapi.presenter.heroes.HeroesPresenter
import com.arttttt.swapi.presenter.heroesadapter.HeroesAdapterContract
import com.arttttt.swapi.presenter.heroesadapter.HeroesAdapterPresenter
import com.arttttt.swapi.presenter.main.MainContract
import com.arttttt.swapi.presenter.main.MainPresenter
import org.koin.dsl.module.module

object AppModule {
    val module = module {
        single { HeroApi.create() }
        single { HeroDatabase.provideHeroDatabase(get()) }

        single { HeroRepositoryImpl(get<HeroDatabase>().getHeroDao(), get()) as HeroRepository }

        factory { (view: HeroesAdapterContract.View) -> HeroesAdapterPresenter(view) as HeroesAdapterContract.Presenter }
        factory { HeroesPresenter(get()) as HeroesContract.Presenter }
        factory { MainPresenter() as MainContract.Presenter }
        factory { HeroDetailsPresenter() as HeroDetailsContract.Presenter }
    }
}