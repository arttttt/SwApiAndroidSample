package com.arttttt.swapisamplemvi.di.modules

import com.arttttt.swapisamplemvi.data.repository.DefaultSwRepository
import com.arttttt.swapisamplemvi.di.PerApplication
import com.arttttt.swapisamplemvi.domain.repository.SwRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @PerApplication
    @Binds
    abstract fun provideSwRepository(repository: DefaultSwRepository): SwRepository
}