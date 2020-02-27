package com.arttttt.swapisamplemvi.ui

import com.arttttt.swapisamplemvi.Screens
import com.arttttt.swapisamplemvi.ui.base.navigation.Coordinator
import com.arttttt.swapisamplemvi.ui.base.navigation.NavigationEvent
import ru.terrakok.cicerone.Router

class RootCoordinator(
    private val router: Router
): Coordinator<RootCoordinator.RootNavigationEvent> {
    sealed class RootNavigationEvent: NavigationEvent {
        object BackPressed: RootNavigationEvent()
        object OpenHeroDetails: RootNavigationEvent()
    }

    override fun accept(event: RootNavigationEvent?) {
        return when (event) {
            is RootNavigationEvent.BackPressed -> router.exit()
            is RootNavigationEvent.OpenHeroDetails -> router.navigateTo(Screens.HeroDetailsScreen)
            else -> Unit
        }
    }
}