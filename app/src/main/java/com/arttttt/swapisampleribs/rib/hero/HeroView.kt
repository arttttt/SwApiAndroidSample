package com.arttttt.swapisampleribs.rib.hero

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.customisation.inflate
import com.arttttt.swapisampleribs.R
import com.arttttt.swapisampleribs.rib.hero.HeroView.Event
import com.arttttt.swapisampleribs.rib.hero.HeroView.ViewModel
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

interface HeroView : RibView,
    ObservableSource<Event>,
    Consumer<ViewModel> {

    sealed class Event

    data class ViewModel(
        val i: Int = 0
    )

    interface Factory : ViewFactory<Nothing?, HeroView>
}


class HeroViewImpl private constructor(
    override val androidView: ViewGroup,
    private val events: PublishRelay<Event> = PublishRelay.create()
) : HeroView,
    ObservableSource<Event> by events,
    Consumer<ViewModel> {

    class Factory(
        @LayoutRes private val layoutRes: Int = R.layout.rib_hero
    ) : HeroView.Factory {
        override fun invoke(deps: Nothing?): (ViewGroup) -> HeroView = {
            HeroViewImpl(
                inflate(it, layoutRes)
            )
        }
    }

    override fun accept(vm: HeroView.ViewModel) {
    }
}
