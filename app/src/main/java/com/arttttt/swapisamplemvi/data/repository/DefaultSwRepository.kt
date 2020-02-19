package com.arttttt.swapisamplemvi.data.repository

import com.arttttt.swapisamplemvi.data.database.dao.HeroesDao
import com.arttttt.swapisamplemvi.data.database.model.HeroDbModel
import com.arttttt.swapisamplemvi.data.network.api.SwApi
import com.arttttt.swapisamplemvi.domain.entity.Hero
import com.arttttt.swapisamplemvi.domain.repository.SwRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
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
                    .insertHeroes(*response.results.map { hero ->
                        HeroDbModel(
                            name = hero.name,
                            birthYear = hero.birthYear,
                            created = hero.created,
                            edited = hero.edited,
                            eyeColor = hero.eyeColor,
                            gender = hero.gender,
                            hairColor = hero.hairColor,
                            height = hero.height,
                            homeWorld = hero.homeWorld,
                            mass = hero.mass,
                            skinColor = hero.skinColor,
                            url = hero.url
                        )
                    }.toTypedArray())
                    .toSingleDefault(Unit)
            }
            .flatMapObservable { heroesDao.getAllHeroes() }
            .startWith(heroesDao.getAllHeroes().take(1).filter(List<HeroDbModel>::isNotEmpty))
            .map { heroes -> heroes.map { hero -> Hero(hero.name, hero.birthYear) } }
            .subscribeOn(Schedulers.io())
    }

    override fun getHeroByName(name: String): Single<Hero> {
        return heroesDao
            .getHeroByName(name)
            .map { hero -> Hero(hero.name, hero.birthYear) }
            .subscribeOn(Schedulers.io())
    }
}