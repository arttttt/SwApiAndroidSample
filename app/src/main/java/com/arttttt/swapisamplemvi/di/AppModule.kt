package com.arttttt.swapisamplemvi.di

import androidx.room.Room
import com.arttttt.swapisamplemvi.BuildConfig
import com.arttttt.swapisamplemvi.data.database.HeroesDatabase
import com.arttttt.swapisamplemvi.ui.herodetails.di.heroDetailsModule
import com.arttttt.swapisamplemvi.ui.heroeslist.di.heroesListModule
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private val appModule = module {
    single {
        Room
            .databaseBuilder(
                get(),
                HeroesDatabase::class.java,
                HeroesDatabase.DB_NAME
            )
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
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
    }
}

val appModules = listOf(
    appModule,
    uiModule,
    repositoryModule,
    apiModule,
    daoModule,
    heroDetailsModule,
    heroesListModule
)