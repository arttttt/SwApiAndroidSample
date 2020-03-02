package com.arttttt.swapisamplemvi.domain.repository

import com.arttttt.swapisamplemvi.domain.entity.Hero
import com.arttttt.swapisamplemvi.domain.entity.Heroes
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface SwRepository {
    fun deleteAllHeroes(): Completable
    fun getHeroesPage(pageIndex: Int): Observable<Heroes>
    fun getHeroByName(name: String): Single<Hero>
}