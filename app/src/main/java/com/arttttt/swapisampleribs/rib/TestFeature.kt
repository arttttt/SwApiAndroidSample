package com.arttttt.swapisampleribs.rib

import android.os.Parcelable
import com.arttttt.swapisampleribs.extensions.toObservable
import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import com.badoo.ribs.core.routing.RoutingSource
import com.badoo.ribs.core.routing.history.Routing
import com.badoo.ribs.core.routing.history.RoutingHistory
import com.badoo.ribs.core.routing.history.RoutingHistoryElement
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.functions.Consumer
import kotlinx.android.parcel.Parcelize

typealias BottomTabs<C> = Set<RoutingHistoryElement<C>>

interface BottomTabOperation<C : Parcelable> : (BottomTabs<C>) -> BottomTabs<C>

private class InitBottomTabs<C : Parcelable>(
    private val configurations: Set<C>
): BottomTabOperation<C> {

    override fun invoke(tabs: BottomTabs<C>): BottomTabs<C> {
        return configurations
            .map { configuration -> RoutingHistoryElement(Routing(configuration)) }
            .toSet()
    }
}

private class MoveToFront<C: Parcelable>(
    val configuration: C
): BottomTabOperation<C> {
    override fun invoke(tabs: BottomTabs<C>): BottomTabs<C> {
        return tabs
    }
}

@Parcelize
data class BottomNavigationFeatureState<C : Parcelable>(
    val bottomTabs: BottomTabs<C> = emptySet()
) : Parcelable, RoutingHistory<C> {

    override fun iterator(): Iterator<RoutingHistoryElement<C>> {
        return bottomTabs.iterator()
    }
}

class TestFeature<C : Parcelable>(
    tabs: Set<C>
): Consumer<TestFeature.Operation<C>>, RoutingSource<C> {

    private val feature = ActorReducerFeature<Operation<C>, Effect<C>, BottomNavigationFeatureState<C>, Nothing>(
        initialState = BottomNavigationFeatureState(),
        actor = object: Actor<BottomNavigationFeatureState<C>, Operation<C>, Effect<C>> {
            override fun invoke(state: BottomNavigationFeatureState<C>, op: Operation<C>): Observable<out Effect<C>> {
                return Effect.Applied(state, op.bottomTabOperation).toObservable()
            }
        },
        bootstrapper = object: Bootstrapper<Operation<C>> {
            override fun invoke(): Observable<Operation<C>> {
                return Operation(InitBottomTabs(tabs)).toObservable()
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
        return BottomNavigationFeatureState()
    }

    override fun remove(identifier: Routing.Identifier) {}

    override fun subscribe(observer: Observer<in RoutingHistory<C>>) {
        feature.subscribe(observer)
    }

    fun moveToFront(configuration: C) {
        feature.accept(Operation(MoveToFront(configuration)))
    }
}