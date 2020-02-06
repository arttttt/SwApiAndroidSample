package com.arttttt.swapisamplemvi.domain.repository

import com.arttttt.swapisamplemvi.domain.entity.Hero
import io.reactivex.Completable
import io.reactivex.Observable

interface SwRepository {
    fun deleteAllHeroes(): Completable
    fun getHeroes(): Observable<List<Hero>>
}