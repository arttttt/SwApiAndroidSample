package com.arttttt.swapisamplemvi.ui.herodetails

import com.arttttt.swapisamplemvi.ui.base.UiActionsDelegate
import com.arttttt.swapisamplemvi.ui.herodetails.di.HeroDetailScope
import javax.inject.Inject

@HeroDetailScope
class HeroDetailsUiActionsDelegate @Inject constructor(): UiActionsDelegate<HeroDetailsFragment.Action>()