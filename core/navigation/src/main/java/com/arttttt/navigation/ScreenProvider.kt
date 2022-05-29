package com.arttttt.navigation

import com.alphicc.brick.Screen

interface ScreenProvider {
    fun <A : Any> provideScreen(args: A): Screen<*>
}
