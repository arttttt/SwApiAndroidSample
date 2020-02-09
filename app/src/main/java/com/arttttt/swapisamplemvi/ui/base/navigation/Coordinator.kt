package com.arttttt.swapisamplemvi.ui.base.navigation

import io.reactivex.functions.Consumer

interface Coordinator<T: NavigationEvent>: Consumer<T>