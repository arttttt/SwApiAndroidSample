package com.arttttt.swapisamplemvi.di

import com.arttttt.swapisamplemvi.App
import com.arttttt.swapisamplemvi.di.modules.*
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@PerApplication
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        ApiModule::class,
        DaoModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent: AndroidInjector<App> {

    @Component.Factory
    interface Factory: AndroidInjector.Factory<App>
}