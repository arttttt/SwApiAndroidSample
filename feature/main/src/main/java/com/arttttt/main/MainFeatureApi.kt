package com.arttttt.main

import com.ewa.module_injector.BaseFeatureApi
import com.github.terrakok.cicerone.Router

interface MainFeatureApi : BaseFeatureApi {
    val router: Router
}
