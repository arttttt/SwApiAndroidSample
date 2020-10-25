package com.arttttt.swapisamplemvi.di.components

import android.app.Application
import android.content.Context
import com.arttttt.swapisamplemvi.di.dependencies.UiComponentDependencies
import com.arttttt.swapisamplemvi.di.modules.ApiModule
import com.arttttt.swapisamplemvi.di.modules.AppModule
import com.arttttt.swapisamplemvi.di.modules.DaoModule
import com.arttttt.swapisamplemvi.di.modules.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ApiModule::class,
        RepositoryModule::class,
        DaoModule::class
    ]
)
interface AppComponent : UiComponentDependencies {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application, @BindsInstance context: Context = application): AppComponent
    }
}