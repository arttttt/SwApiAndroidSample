package com.arttttt.swapisampleribs.rib.bottom_navigation.feature

import android.os.Bundle
import android.os.Parcelable
import com.arttttt.swapisampleribs.extensions.toObservable
import com.arttttt.swapisampleribs.rib.bottom_navigation.feature.operations.InitBottomTabs
import com.arttttt.swapisampleribs.rib.bottom_navigation.feature.operations.MoveToFront
import com.badoo.mvicore.android.AndroidTimeCapsule
import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.element.TimeCapsule
import com.badoo.mvicore.feature.ActorReducerFeature
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.routing.RoutingSource
import com.badoo.ribs.core.routing.history.Routing
import com.badoo.ribs.core.routing.history.RoutingHistory
import com.badoo.ribs.core.routing.history.RoutingHistoryElement
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.functions.Consumer

private val timeCapsuleKey = BottomNavigationFeature::class.java.name
private fun <C : Parcelable> TimeCapsule<BottomNavigationFeatureState<C>>.initialState(): BottomNavigationFeatureState<C> =
    (get(timeCapsuleKey) ?: BottomNavigationFeatureState())

//todo: refactoring
class BottomNavigationFeature<C : Parcelable> internal constructor(
    tabs: Set<C>,
    private val timeCapsule: AndroidTimeCapsule
): Consumer<BottomNavigationFeature.Operation<C>>, RoutingSource<C> {

    constructor(
        tabs: Set<C>,
        buildParams: BuildParams<*>
    ) : this(
        tabs,
        AndroidTimeCapsule(buildParams.savedInstanceState)
    )

    init {
        timeCapsule.register(timeCapsuleKey) { feature.state }
    }

    private val feature = ActorReducerFeature<Operation<C>, Effect<C>, BottomNavigationFeatureState<C>, Nothing>(
        initialState = timeCapsule.initialState(),
        actor = object: Actor<BottomNavigationFeatureState<C>, Operation<C>, Effect<C>> {
            override fun invoke(state: BottomNavigationFeatureState<C>, op: Operation<C>): Observable<out Effect<C>> {
                return Effect.Applied(
                    state,
                    op.bottomTabOperation
                ).toObservable()
            }
        },
        bootstrapper = object: Bootstrapper<Operation<C>> {
            override fun invoke(): Observable<Operation<C>> {
                return Operation(
                    InitBottomTabs(
                        tabs
                    )
                ).toObservable()
            }
        },
        newsPublisher = null,
        reducer = object: Reducer<BottomNavigationFeatureState<C>, Effect<C>> {
            override fun invoke(state: BottomNavigationFeatureState<C>, effect: Effect<C>): BottomNavigationFeatureState<C> {
                return when (effect) {
                    is Effect.Applied -> {
                        when (effect.bottomTabOperation) {
                            is InitBottomTabs<C> -> {
                                val updated = effect
                                    .bottomTabOperation.invoke(state.bottomTabs)
                                    .mapIndexed { index, element ->
                                        element.copy(
                                            activation = if (index == 0)
                                                RoutingHistoryElement.Activation.ACTIVE
                                            else
                                                RoutingHistoryElement.Activation.INACTIVE,
                                            routing = element.routing.copy(
                                                identifier = Routing.Identifier(
                                                    element.routing.hashCode()
                                                )
                                            )
                                        )
                                    }
                                    .toSet()

                                state.copy(
                                    bottomTabs = updated
                                )
                            }

                            is MoveToFront<C> -> {
                                state.copy(
                                    bottomTabs = state
                                        .bottomTabs
                                        .map { tab ->
                                            tab.copy(
                                                activation = if (tab.routing.configuration == effect.bottomTabOperation.configuration)
                                                    RoutingHistoryElement.Activation.ACTIVE
                                                else
                                                    RoutingHistoryElement.Activation.INACTIVE
                                            )
                                        }
                                        .toSet()
                                )
                            }

                            else -> state
                        }
                    }
                }
            }
        }
    )

    data class Operation<C : Parcelable>(val bottomTabOperation: BottomTabOperation<C>)

    sealed class Effect<C : Parcelable> {
        abstract val oldState: BottomNavigationFeatureState<C>

        data class Applied<C : Parcelable>(
            override val oldState: BottomNavigationFeatureState<C>,
            val bottomTabOperation: BottomTabOperation<C>
        ) : Effect<C>()
    }

    override fun accept(operation: Operation<C>) {
        feature.accept(operation)
    }

    override fun baseLineState(fromRestored: Boolean): RoutingHistory<C> {
        return timeCapsule.initialState()
    }

    override fun remove(identifier: Routing.Identifier) {}

    override fun subscribe(observer: Observer<in RoutingHistory<C>>) {
        feature.subscribe(observer)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        timeCapsule.saveState(outState)
    }

    fun moveToFront(configuration: C) {
        feature.accept(
            Operation(
                MoveToFront(
                    configuration
                )
            )
        )
    }
}