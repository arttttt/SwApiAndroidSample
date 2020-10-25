package com.arttttt.swapisamplemvi.di.modules

import com.arttttt.swapisamplemvi.data.network.api.SwApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
object ApiModule {
    @JvmStatic
    @Provides
    @Singleton
    fun provideSwApi(retrofit: Retrofit): SwApi {
        return retrofit.create()
    }
}