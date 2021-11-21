package com.arttttt.hero.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.arkivanov.mvikotlin.core.utils.diff
import com.arkivanov.mvikotlin.core.view.BaseMviView
import com.arkivanov.mvikotlin.core.view.ViewRenderer
import com.arttttt.hero.R
import com.arttttt.hero.ui.adapter.delegate.HeroPropertyAdapterDelegate
import com.arttttt.recyclerview.EqualsDiffCallback
import com.arttttt.recyclerview.ListDifferAdapter
import com.arttttt.recyclerview.adapter.delegates.LoadingAdapterDelegate
import com.google.android.material.appbar.MaterialToolbar

internal class HeroViewImpl(root: View) : BaseMviView<HeroView.Model, HeroView.UiEvent>(), HeroView {

    private val adapter = ListDifferAdapter(
        diffCallback = EqualsDiffCallback(),
        delegates = setOf(
            LoadingAdapterDelegate(),
            HeroPropertyAdapterDelegate()
        )
    )

    private val toolbar = root.findViewById<MaterialToolbar>(R.id.toolbar)

    override val renderer: ViewRenderer<HeroView.Model> = diff {
        diff(get = HeroView.Model::items, set = adapter::setItems)
	    diff(get = HeroView.Model::title, set = { title ->
		    if (!toolbar.title.isNullOrEmpty()) {
			    TransitionManager.beginDelayedTransition(root as ViewGroup, ChangeText().apply {
				    changeBehavior = ChangeText.CHANGE_BEHAVIOR_OUT_IN
			    })
		    }
		
		    toolbar.title = title
	    })
    }

    init {
        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.adapter = adapter

        toolbar.setNavigationOnClickListener { dispatch(HeroView.UiEvent.BackPressed) }
    }

}
