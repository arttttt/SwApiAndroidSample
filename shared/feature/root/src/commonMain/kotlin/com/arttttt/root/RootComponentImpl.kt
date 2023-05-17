package com.arttttt.root

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
import com.arttttt.arch.component.ComponentFactory
import com.arttttt.arch.component.Configuration
import com.arttttt.arch.component.DecomposeComponent
import com.arttttt.arch.extensions.stackComponentEvents
import com.arttttt.arch.view.ViewOwner
import com.arttttt.hero.api.HeroComponent
import com.arttttt.hero.impl.HeroComponentHolder
import com.arttttt.heroeslist.api.components.HeroesListComponent
import com.arttttt.heroeslist.impl.HeroesListComponentHolder
import com.arttttt.root.view.RootComponentView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filterIsInstance

class RootComponentImpl(
    componentContext: ComponentContext,
    viewFactory: (RootComponent) -> RootComponentView,
) : ComponentContext by componentContext,
    RootComponent,
    ViewOwner<RootComponentView> {

    sealed class Screen : Configuration {

        @Parcelize
        object HeroesList : Screen()

        @Parcelize
        data class Hero(
            val hero: com.arttttt.heroeslist.api.entity.Hero,
        ) : Screen()
    }

    override val view: RootComponentView = viewFactory.invoke(this)

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
                .stackComponentEvents<HeroComponent.Event>()
                .filterIsInstance<HeroComponent.Event.BackPressed>()
                .bindTo {
                    navigation.pop()
                }

            _childStack
                .stackComponentEvents<HeroesListComponent.Event>()
                .filterIsInstance<HeroesListComponent.Event.HeroClicked>()
                .bindTo { event ->
                    navigation.push(Screen.Hero(event.hero))
                }
        }
    }
}