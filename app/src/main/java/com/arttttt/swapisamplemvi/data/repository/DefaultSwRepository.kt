package com.arttttt.swapisamplemvi.data.repository

import com.arttttt.swapisamplemvi.data.database.HeroDbModel
import com.arttttt.swapisamplemvi.data.database.HeroesDao
import com.arttttt.swapisamplemvi.data.network.api.SwApi
import com.arttttt.swapisamplemvi.domain.entity.Hero
import com.arttttt.swapisamplemvi.domain.repository.SwRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class DefaultSwRepository(
    private val swApi: SwApi,
    private val heroesDao: HeroesDao
): SwRepository {

    override fun deleteAllHeroes(): Completable {
        return heroesDao
            .clearHeroes()
            .subscribeOn(Schedulers.io())
    }

    override fun getHeroes(): Observable<List<Hero>> {
        return swApi
            .searchHero()
            .flatMap { response ->
                heroesDao
                    .insertHeroes(*response.results.map { hero -> HeroDbModel(hero.name) }.toTypedArray())
                    .toSingleDefault(Unit)
            }
            .flatMap { heroesDao.getAllHeroes() }
            .toObservable()
            .startWith(heroesDao.getAllHeroes().toObservable().filter(List<HeroDbModel>::isNotEmpty))
            .map { heroes -> heroes.map { hero -> Hero(hero.name) } }
            .subscribeOn(Schedulers.io())
    }
}