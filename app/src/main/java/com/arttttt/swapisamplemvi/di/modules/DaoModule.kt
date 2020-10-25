package com.arttttt.swapisamplemvi.di.modules

import com.arttttt.swapisamplemvi.data.database.HeroesDatabase
import com.arttttt.swapisamplemvi.data.database.dao.HeroesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DaoModule {
    @JvmStatic
    @Provides
    @Singleton
    fun provideHeroDao(db: HeroesDatabase): HeroesDao {
        return db.getHeroesDao()
    }
}