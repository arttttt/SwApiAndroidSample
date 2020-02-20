package com.arttttt.swapisamplemvi.di.modules

import com.arttttt.swapisamplemvi.data.database.HeroesDatabase
import com.arttttt.swapisamplemvi.data.database.dao.HeroesDao
import com.arttttt.swapisamplemvi.di.PerApplication
import dagger.Module
import dagger.Provides

@Module
class DaoModule {
    @PerApplication
    @Provides
    fun provideHeroesDao(db: HeroesDatabase): HeroesDao {
        return db.getHeroesDao()
    }
}