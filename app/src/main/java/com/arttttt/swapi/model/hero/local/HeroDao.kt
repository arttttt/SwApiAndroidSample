package com.arttttt.swapi.model.hero.local

import android.arch.persistence.room.*
import android.content.Context
import com.arttttt.swapi.model.hero.Hero
import io.reactivex.Single

@Dao
interface HeroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(hero: Hero)

    @Query("SELECT * FROM hero WHERE name LIKE '%' || :name  || '%'")
    fun getHero(name: String): Single<List<Hero>>

    @Query("SELECT * FROM hero")
    fun gelAllHeroes(): Single<List<Hero>>
}