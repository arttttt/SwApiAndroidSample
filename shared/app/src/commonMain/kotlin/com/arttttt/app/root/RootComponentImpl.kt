package com.arttttt.app.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arttttt.app.asStateFlow
import com.arttttt.app.hero.HeroComponent
import com.arttttt.app.hero.HeroComponentImpl
import com.arttttt.app.heroeslist.HeroesListComponent
import com.arttttt.app.heroeslist.HeroesListComponentImpl
import com.arttttt.app.heroeslist.domain.entity.Hero
import com.arttttt.arch.component.ComponentBuilder
import com.arttttt.arch.component.ComponentFactory
import com.arttttt.arch.component.ComponentFactoryImpl
import com.arttttt.arch.component.Configuration
import com.arttttt.arch.component.DecomposeComponent
import com.arttttt.arch.view.ViewOwner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take

class RootComponentImpl(
    componentContext: ComponentContext,
) : ComponentContext by componentContext,
    RootComponent,
    ViewOwner<RootComponentView> {

    private sealed class Config : Configuration {

        @Parcelize
        object HeroesList : Config()

        @Parcelize
        data class Hero(
            val hero: com.arttttt.app.heroeslist.domain.entity.Hero,
        ) : Config()
    }

    override val view: RootComponentView by ViewOwner.create { RootComponentViewImpl(this) }::view

    private val factory: ComponentFactory<Configuration> = ComponentFactoryImpl(
        builders = mapOf(
            Config.HeroesList::class to ComponentBuilder<Config.HeroesList, HeroesListComponent> { _, componentContext ->
                HeroesListComponentImpl(
                    componentContext = componentContext,
                )
            },
            Config.Hero::class to ComponentBuilder { config: Config.Hero, componentContext ->
                HeroComponentImpl(
                    hero = config.hero,
                    componentContext = componentContext,
                )
            }
        ),
    )

    private val navigation = StackNavigation<Config>()

    private val _childStack = childStack(
        source = navigation,
        initialConfiguration = Config.HeroesList,
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
                    navigation.push(Config.Hero(event.hero))
                }
        }
    }
}
