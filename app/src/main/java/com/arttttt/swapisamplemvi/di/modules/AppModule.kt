package com.arttttt.swapisamplemvi.di.modules

import android.content.Context
import androidx.room.Room
import com.arttttt.swapisamplemvi.App
import com.arttttt.swapisamplemvi.BuildConfig
import com.arttttt.swapisamplemvi.data.database.HeroesDatabase
import com.arttttt.swapisamplemvi.di.PerApplication
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule {

    @PerApplication
    @Provides
    fun provideContext(app: App): Context {
        return app
    }

    @PerApplication
    @Provides
    fun provideDatabase(context: Context): HeroesDatabase {
        return Room
            .databaseBuilder(
                context,
                HeroesDatabase::class.java,
                HeroesDatabase.DB_NAME
            )
            .build()
    }

    @PerApplication
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
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