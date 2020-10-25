package com.arttttt.swapisamplemvi.di.dependencies

import com.arttttt.swapisamplemvi.domain.repository.SwRepository

interface UiComponentDependencies {
    fun provideSwRepository(): SwRepository
}