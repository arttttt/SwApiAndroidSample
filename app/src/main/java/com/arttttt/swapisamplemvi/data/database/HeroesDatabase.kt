package com.arttttt.swapisamplemvi.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arttttt.swapisamplemvi.data.database.dao.HeroesDao
import com.arttttt.swapisamplemvi.data.database.model.HeroDbModel

@Database(
    entities = [
        HeroDbModel::class
    ],
    version = 1
)
abstract class HeroesDatabase: RoomDatabase() {

    companion object {
        const val DB_NAME = "HEROES_DB"
    }

    abstract fun getHeroesDao(): HeroesDao
}