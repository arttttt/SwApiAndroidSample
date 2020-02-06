package com.arttttt.swapisamplemvi.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arttttt.swapisamplemvi.data.database.model.HeroDbModel
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface HeroesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHeroes(vararg heroes: HeroDbModel): Completable

    @Query("delete from heroes_table")
    fun clearHeroes(): Completable

    @Query("select * from heroes_table")
    fun getAllHeroes(): Single<List<HeroDbModel>>
}