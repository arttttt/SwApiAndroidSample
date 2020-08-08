package com.arttttt.swapicompose.data.repository

import com.arttttt.swapicompose.data.network.api.SwApi
import com.arttttt.swapicompose.domain.entity.Hero
import com.arttttt.swapicompose.domain.entity.Heroes
import com.arttttt.swapicompose.domain.repository.SwRepository
import io.reactivex.Observable

class DefaultSwRepository(
    private val swApi: SwApi
): SwRepository {
    override fun getHeroesPage(pageIndex: Int): Observable<Heroes> {
        return swApi
            .searchHero(pageIndex)
            .map { response ->
                Heroes(
                    isAllHeroesLoaded = response.next == null,
                    heroes = response.results.map { Hero(it.name, it.birthYear) }
                )
            }
            .toObservable()
    }
}