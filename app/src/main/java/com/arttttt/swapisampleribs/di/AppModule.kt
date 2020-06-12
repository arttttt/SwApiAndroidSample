package com.arttttt.swapisampleribs.di

import com.arttttt.swapisampleribs.BuildConfig
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
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