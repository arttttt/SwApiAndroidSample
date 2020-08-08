package com.arttttt.swapicompose.domain.repository

import com.arttttt.swapicompose.domain.entity.Heroes
import io.reactivex.Observable

interface SwRepository {
    fun getHeroesPage(pageIndex: Int): Observable<Heroes>
}