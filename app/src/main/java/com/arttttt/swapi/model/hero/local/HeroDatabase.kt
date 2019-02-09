package com.arttttt.swapi.model.hero.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.arttttt.swapi.model.hero.Hero

@Database(entities = [Hero::class], version = 1)
abstract class HeroDatabase: RoomDatabase() {
    companion object {
        private const val DbName = "db_heroes"

        fun provideHeroDatabase(context: Context): HeroDatabase =
            Room.databaseBuilder(context, HeroDatabase::class.java, HeroDatabase.DbName)
                .fallbackToDestructiveMigration()
                .build()
    }

    abstract fun getHeroDao(): HeroDao
}