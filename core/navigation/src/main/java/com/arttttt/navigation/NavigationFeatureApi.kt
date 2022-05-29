package com.arttttt.navigation

import com.alphicc.brick.TreeRouter
import com.arttttt.moduleinjector.BaseFeatureApi

interface NavigationFeatureApi : BaseFeatureApi {

    val router: TreeRouter
}
