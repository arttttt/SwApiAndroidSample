package com.arttttt.main

import com.alphicc.brick.Screen
import com.alphicc.brick.TreeRouter
import com.arttttt.moduleinjector.BaseFeatureDependencies

interface MainFeatureDependencies : BaseFeatureDependencies {

    val router: TreeRouter
    val rootScreen: Screen<*>
}
