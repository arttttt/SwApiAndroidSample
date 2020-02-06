package com.arttttt.swapisamplemvi.di

import androidx.room.Room
import com.arttttt.swapisamplemvi.BuildConfig
import com.arttttt.swapisamplemvi.data.database.HeroesDao
import com.arttttt.swapisamplemvi.data.database.HeroesDatabase
import com.arttttt.swapisamplemvi.data.network.api.SwApi
import com.arttttt.swapisamplemvi.data.repository.DefaultSwRepository
import com.arttttt.swapisamplemvi.domain.repository.SwRepository
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

val appModule = module {
    single<SwApi> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(MoshiConverterFactory.create())
            .client(
                OkHttpClient
                    .Builder()
                    .addInterceptor(
                        LoggingInterceptor
                            .Builder()
                            .log(Platform.INFO)
                            .loggable(BuildConfig.DEBUG)
                            .request("Request")
                            .response("Response")
                            .setLevel(Level.BODY)
                            .build()
                    )
                    .build()
            )
            .build()
            .create()
    }

    single<SwRepository>{
        DefaultSwRepository(
            swApi = get(),
            heroesDao = get()
        )
    }

    single {
        Room
            .databaseBuilder(get(), HeroesDatabase::class.java, HeroesDatabase.DB_NAME)
            .build()
    }

    single { get<HeroesDatabase>().getHeroesDao() }
}