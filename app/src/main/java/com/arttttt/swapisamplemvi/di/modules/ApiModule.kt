package com.arttttt.swapisamplemvi.di.modules

import com.arttttt.swapisamplemvi.data.network.api.SwApi
import com.arttttt.swapisamplemvi.di.PerApplication
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
class ApiModule {

    @PerApplication
    @Provides
    fun provideSwApi(retrofit: Retrofit): SwApi {
        return retrofit.create()
    }
}