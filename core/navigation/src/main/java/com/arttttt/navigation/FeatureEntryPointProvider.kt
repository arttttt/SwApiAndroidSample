package com.arttttt.navigation

import com.github.terrakok.cicerone.Screen

fun interface FeatureEntryPointProvider<A: Any> {
    fun provideEntryPoint(arguments: A): Screen
}
