package com.arttttt.swapisamplemvi.ui.herodetails

import com.arttttt.swapisamplemvi.R
import com.arttttt.swapisamplemvi.ui.base.IBackHandler
import com.arttttt.swapisamplemvi.ui.base.UiEvent
import com.arttttt.swapisamplemvi.ui.base.ViewController

class HeroDetailsViewController: ViewController<HeroDetailsViewController.HeroDetailsUiEvent, HeroDetailsViewModel>(), IBackHandler {
    sealed class HeroDetailsUiEvent: UiEvent {
        object BackPressed: HeroDetailsUiEvent()
    }

    override val layoutRes: Int = R.layout.fragment_hero_details

    override fun onBackPressed() {
        uiEvents.accept(HeroDetailsUiEvent.BackPressed)
    }
}