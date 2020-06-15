package com.arttttt.swapisampleribs.rib.movies_list.feature

import com.arttttt.swapisampleribs.domain.entity.Movie
import com.arttttt.swapisampleribs.domain.repository.MoviesRepository
import com.arttttt.swapisampleribs.extensions.toObservable
import com.arttttt.swapisampleribs.rib.movies_list.feature.MoviesListFeature.*
import com.arttttt.swapisampleribs.rib.movies_list.feature.MoviesListFeature.Action.Execute
import com.badoo.mvicore.element.*
import com.badoo.mvicore.feature.BaseFeature
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

class MoviesListFeature(
    moviesRepository: MoviesRepository
) : BaseFeature<Wish, Action, Effect, State, News>(
    initialState = State(
        movies = emptyList()
    ),
    wishToAction = { Execute(it) },
    bootstrapper = BootStrapperImpl(),
    actor = ActorImpl(
        moviesRepository = moviesRepository
    ),
    reducer = ReducerImpl(),
    postProcessor = PostProcessorImpl(),
    newsPublisher = NewsPublisherImpl()
) {

    data class State(
        val movies: List<Movie>
    )

    sealed class Wish

    sealed class Action {
        class Execute(val wish: Wish) : Action()
        object LoadInitialData: Action()
    }

    sealed class Effect {
        class MoviesLoaded(val movies: List<Movie>): Effect()
    }

    sealed class News

    class BootStrapperImpl : Bootstrapper<Action> {
        override fun invoke(): Observable<Action> {
            return Action.LoadInitialData.toObservable()
        }
    }

    class ActorImpl(
        private val moviesRepository: MoviesRepository
    ) : Actor<State, Action, Effect> {
        override fun invoke(state: State, action: Action): Observable<out Effect>{
            return when (action) {
                is Execute -> dispatchWish(state, action.wish)
                is Action.LoadInitialData -> moviesRepository
                    .loadMovies()
                    .map { movies -> Effect.MoviesLoaded(movies) }
                    .toObservable()
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }

        private fun dispatchWish(state: State, wish: Wish): Observable<out Effect> {
            return Observable.empty()
        }
    }

    class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State {
            return when (effect) {
                is Effect.MoviesLoaded -> state.copy(
                    movies = effect.movies
                )
            }
        }
    }

    class PostProcessorImpl : PostProcessor<Action, Effect, State> {
        override fun invoke(action: Action, effect: Effect, state: State): Action? {
            return null
        }
    }

    class NewsPublisherImpl : NewsPublisher<Action, Effect, State, News> {
        override fun invoke(action: Action, effect: Effect, state: State): News?{
            return null
        }
    }
}
