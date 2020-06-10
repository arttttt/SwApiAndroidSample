package com.arttttt.swapisampleribs.container.rib.heroes_list.feature

import com.arttttt.swapisampleribs.BuildConfig
import com.arttttt.swapisampleribs.container.data.network.api.SwApi
import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.NewsPublisher
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import com.arttttt.swapisampleribs.container.rib.heroes_list.feature.HeroesListFeature.Effect
import com.arttttt.swapisampleribs.container.rib.heroes_list.feature.HeroesListFeature.News
import com.arttttt.swapisampleribs.container.rib.heroes_list.feature.HeroesListFeature.State
import com.arttttt.swapisampleribs.container.rib.heroes_list.feature.HeroesListFeature.Wish
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import io.reactivex.Observable
import io.reactivex.Observable.empty
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

internal class HeroesListFeature : ActorReducerFeature<Wish, Effect, State, News>(
    initialState = State(),
    bootstrapper = BootStrapperImpl(),
    actor = ActorImpl(),
    reducer = ReducerImpl(),
    newsPublisher = NewsPublisherImpl()
) {

    data class State(
        val yourData: Any? = null
    )

    sealed class Wish {
        object Test: Wish()
    }

    sealed class Effect

    sealed class News

    class BootStrapperImpl : Bootstrapper<Wish> {
        override fun invoke(): Observable<Wish> {
            return Observable.just(Wish.Test)
        }
    }

    class ActorImpl : Actor<State, Wish, Effect> {

        private val swApi = Retrofit.Builder()
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
            .create<SwApi>()

        override fun invoke(state: State, wish: Wish): Observable<Effect> {
            return when (wish) {
                is Wish.Test -> swApi
                    .searchHero(1)
                    .flatMapObservable { empty<Effect>() }
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }

    class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State =
            state
    }

    class NewsPublisherImpl : NewsPublisher<Wish, Effect, State, News> {
        override fun invoke(wish: Wish, effect: Effect, state: State): News? =
            null
    }
}
