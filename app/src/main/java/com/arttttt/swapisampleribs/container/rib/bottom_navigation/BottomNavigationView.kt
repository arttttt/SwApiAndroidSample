package com.arttttt.swapisampleribs.container.rib.bottom_navigation

import android.view.Menu
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.customisation.inflate
import com.arttttt.swapisampleribs.R
import com.arttttt.swapisampleribs.container.rib.bottom_navigation.BottomNavigationView.Event
import com.arttttt.swapisampleribs.container.rib.bottom_navigation.BottomNavigationView.ViewModel
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

interface BottomNavigationView : RibView,
    ObservableSource<Event>,
    Consumer<ViewModel> {

    sealed class Event

    data class ViewModel(
        val i: Int = 0
    )

    interface Factory : ViewFactory<Nothing?, BottomNavigationView>
}


class BottomNavigationViewImpl private constructor(
    override val androidView: ViewGroup,
    private val events: PublishRelay<Event> = PublishRelay.create()
) : BottomNavigationView,
    ObservableSource<Event> by events,
    Consumer<ViewModel> {

    class Factory(
        @LayoutRes private val layoutRes: Int = R.layout.rib_bottom_navigation
    ) : BottomNavigationView.Factory {
        override fun invoke(deps: Nothing?): (ViewGroup) -> BottomNavigationView = {
            BottomNavigationViewImpl(
                inflate(it, layoutRes)
            )
        }
    }

    private val bottomNavigation = androidView.findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottomNavigation)

    init {
        //todo: move to resources
        bottomNavigation.menu.add(0, 0, Menu.NONE, "Heroes")
        bottomNavigation.menu.add(0, 1, Menu.NONE, "Movies")
    }

    override fun accept(vm: BottomNavigationView.ViewModel) {
    }
}
