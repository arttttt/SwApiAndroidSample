package com.arttttt.hero.impl.ui

import com.arttttt.arch.view.PlatformAbstractComponentView
import com.arttttt.hero.api.HeroView

abstract class PlatformHeroView : PlatformAbstractComponentView<HeroView.Model, HeroView.UiEvent>(),
    HeroView