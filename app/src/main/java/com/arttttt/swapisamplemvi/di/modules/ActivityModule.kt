package com.arttttt.swapisamplemvi.di.modules

import com.arttttt.swapisamplemvi.di.PerActivity
import com.arttttt.swapisamplemvi.ui.AppActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @PerActivity
    @ContributesAndroidInjector(modules = [UiModule::class])
    abstract fun provideAppActivity(): AppActivity
}