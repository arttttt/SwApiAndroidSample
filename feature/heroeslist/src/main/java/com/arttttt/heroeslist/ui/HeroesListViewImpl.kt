package com.arttttt.heroeslist.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.arkivanov.mvikotlin.core.utils.diff
import com.arkivanov.mvikotlin.core.view.BaseMviView
import com.arkivanov.mvikotlin.core.view.ViewRenderer
import com.arttttt.heroeslist.R
import com.arttttt.heroeslist.ui.adapter.delegate.HeroAdapterDelegate
import com.arttttt.recyclerview.EqualsDiffCallback
import com.arttttt.recyclerview.ListDifferAdapter
import com.arttttt.recyclerview.LoadMoreCallback
import com.arttttt.recyclerview.adapter.delegates.LoadMoreAdapterDelegate
import com.arttttt.recyclerview.adapter.delegates.LoadingAdapterDelegate

internal class HeroesListViewImpl(root: View) : BaseMviView<HeroesListView.Model, HeroesListView.UiEvent>(), HeroesListView {

    private val adapter = ListDifferAdapter(
        diffCallback = EqualsDiffCallback(),
        delegates = setOf(
            LoadingAdapterDelegate(),
            LoadMoreAdapterDelegate(),
            HeroAdapterDelegate(
                onClick = { id ->
                    dispatch(
                        HeroesListView.UiEvent.HeroClicked(
                            id = id
                        )
                    )
                }
            )
        )
    )

    override val renderer: ViewRenderer<HeroesListView.Model> = diff {
        diff(get = HeroesListView.Model::items, set = adapter::setItems)
    }

    init {
        root.findViewById<RecyclerView>(R.id.recycler_view).also { recyclerView ->
            recyclerView.adapter = adapter
        }

        adapter.loadMoreCallback = LoadMoreCallback {
            dispatch(HeroesListView.UiEvent.LoadMore)
        }
    }

}
