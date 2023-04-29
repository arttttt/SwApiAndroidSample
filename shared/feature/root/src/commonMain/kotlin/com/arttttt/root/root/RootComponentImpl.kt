package com.arttttt.root.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arttttt.root.asStateFlow
import com.arttttt.arch.component.ComponentFactory
import com.arttttt.arch.component.Configuration
import com.arttttt.arch.component.DecomposeComponent
import com.arttttt.arch.view.ViewOwner
import com.arttttt.hero.api.HeroComponent
import com.arttttt.hero.impl.HeroComponentHolder
import com.arttttt.heroeslist.api.HeroesListComponent
import com.arttttt.heroeslist.impl.HeroesListComponentHolder
import com.arttttt.root.root.view.RootComponentView
import com.arttttt.root.root.view.RootComponentViewImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class RootComponentImpl(
    componentContext: ComponentContext,
) : ComponentContext by componentContext,
    RootComponent,
    ViewOwner<RootComponentView> {

    sealed class Screen : Configuration {

        @Parcelize
        object HeroesList : Screen()

        @Parcelize
        data class Hero(
            val hero: com.arttttt.heroeslist.api.Hero,
        ) : Screen()
    }

    override val view: RootComponentView by ViewOwner.create {
        RootComponentViewImpl(
            this
        )
    }::view

    private val factory: ComponentFactory<Screen> = RootComponentFactory(
        /**
         * todo: provide through dependencies
         */
        heroesListComponentBuilder = HeroesListComponentHolder.api.heroesListComponentBuilder,
        heroComponentBuilder = HeroComponentHolder.api.heroComponentBuilder,
    )

    private val navigation = StackNavigation<Screen>()

    private val _childStack = childStack(
        source = navigation,
        initialConfiguration = Screen.HeroesList,
        handleBackButton = true,
        childFactory = factory::createComponent,
    )

    override val childStack: Value<ChildStack<*, DecomposeComponent>> = _childStack

    init {
        bind(
            lifecycle = lifecycle,
            mode = BinderLifecycleMode.CREATE_DESTROY,
            mainContext = Dispatchers.Main,
        ) {

            _childStack
                .asStateFlow()
                .map { stack -> stack.active.instance }
                .filterIsInstance<HeroComponent>()
                .flatMapLatest { component -> component.events }
                .filterIsInstance<HeroComponent.Event.BackPressed>()
                .bindTo {
                    navigation.pop()
                }

            _childStack
                .asStateFlow()
                .map { stack -> stack.active.instance }
                .filterIsInstance<HeroesListComponent>()
                .flatMapLatest { component -> component.events }
                .filterIsInstance<HeroesListComponent.Event.HeroClicked>()
                .bindTo { event ->
                    navigation.push(Screen.Hero(event.hero))
                }
        }
    }
}
