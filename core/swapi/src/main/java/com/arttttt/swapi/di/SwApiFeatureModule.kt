package com.arttttt.swapi.di

import com.arttttt.dagger2.PerFeature
import com.arttttt.swapi.data.api.SwApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
internal abstract class SwApiFeatureModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @PerFeature
        fun provideSwApi(): SwApi {
            return Retrofit.Builder()
                .baseUrl("https://www.swapi.tech/api/")
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder().create()
                    )
                )
                .build()
                .create()
        }
    }
}
