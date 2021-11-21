package com.arttttt.hero.di

import com.arttttt.dagger2.PerFeature
import com.arttttt.hero.BuildConfig
import com.arttttt.hero.data.network.api.HeroApi
import com.arttttt.hero.data.repository.HeroRepositoryImpl
import com.arttttt.hero.domain.repository.HeroRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
internal abstract class HeroFeatureModule {

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
        fun provideHeroesListApi(retrofit: Retrofit): HeroApi {
            return retrofit.create()
        }

    }

    @Binds
    @PerFeature
    abstract fun provideHeroRepository(impl: HeroRepositoryImpl): HeroRepository

}
