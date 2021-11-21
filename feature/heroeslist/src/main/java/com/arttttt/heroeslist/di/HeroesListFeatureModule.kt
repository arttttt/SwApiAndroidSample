package com.arttttt.heroeslist.di

import com.arttttt.dagger2.PerFeature
import com.arttttt.heroeslist.BuildConfig
import com.arttttt.heroeslist.data.network.api.HeroesListApi
import com.arttttt.heroeslist.data.repository.HeroesListRepositoryImpl
import com.arttttt.heroeslist.domain.repository.HeroesListRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
internal abstract class HeroesListFeatureModule {

    @Module
    companion object {

        @Provides
        @PerFeature
        fun provideGson(): Gson {
            return GsonBuilder()
                .create()
        }

        @Provides
        @PerFeature
        fun provideRetrofit(gson: Gson): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(
                    GsonConverterFactory.create(
                        gson
                    )
                )
                .build()
        }

        @Provides
        @PerFeature
        fun provideHeroesListApi(retrofit: Retrofit): HeroesListApi {
            return retrofit.create()
        }

    }

    @Binds
    @PerFeature
    abstract fun provideHeroesListRepository(impl: HeroesListRepositoryImpl): HeroesListRepository

}
