package com.arttttt.swapi.model.hero.repository

import com.arttttt.swapi.model.hero.Hero
import com.arttttt.swapi.model.hero.local.HeroDao
import com.arttttt.swapi.model.hero.remote.HeroApi
import io.reactivex.Single

class HeroRepositoryImpl(private val heroDao: HeroDao,
                         private val heroApi: HeroApi): HeroRepository {
    override fun getHero(name: String): Single<List<Hero>> {
        return heroApi.searchHero(name)
            .flattenAsObservable { it.results }
            .doOnNext { heroDao.insert(it) }
            .toList()
            .onErrorResumeNext{ heroDao.getHero(name) }
    }

    override fun getAllHeroes(): Single<List<Hero>> {
        return heroDao.gelAllHeroes()
    }
}