package com.arttttt.swapisamplemvi.di.modules

import com.arttttt.swapisamplemvi.data.repository.SwRepositoryImpl
import com.arttttt.swapisamplemvi.domain.repository.SwRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {
    @Binds
    @Singleton
    fun provideSwApiRepository(impl: SwRepositoryImpl): SwRepository
}