package com.arttttt.app.heroeslist.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import com.arttttt.app.heroeslist.HeroesListComponent
import com.arttttt.app.heroeslist.ui.lazylist.HeroLazyListDelegate
import com.arttttt.app.heroeslist.ui.lazylist.ProgressAdapterDelegate
import com.arttttt.arch.component.DecomposeComponent
import com.arttttt.arch.view.AndroidAbstractComponentView
import com.arttttt.arch.view.lazylist.dsl.rememberLazyListDelegateManager
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach

actual class HeroesListViewImpl actual constructor(
    actual val component: HeroesListComponent
) : AndroidAbstractComponentView<HeroesListView.Model, HeroesListView.UiEvent>(),
    HeroesListView {

    override val initialState: HeroesListView.Model = HeroesListView.Model(
        items = persistentListOf()
    )

    @Composable
    override fun Content(
        model: HeroesListView.Model,
        modifier: Modifier,
    ) {

        val lazyListState = rememberLazyListState()

        val lazyListDelegatesManager = rememberLazyListDelegateManager(
            delegates = listOf(
                HeroLazyListDelegate(
                    onClick = { name ->
                        dispatch(HeroesListView.UiEvent.HeroClicked(name))
                    },
                ),
                ProgressAdapterDelegate(),
            ),
        )

        LazyColumn(
            state = lazyListState,
            modifier = Modifier.fillMaxSize(),
        ) {

            items(
                items = model.items,
                key = lazyListDelegatesManager::getKey,
                contentType = lazyListDelegatesManager::getContentType,
            ) { item ->
                lazyListDelegatesManager.Content(
                    context = this,
                    item = item,
                    modifier = Modifier,
                )
            }
        }

        lazyListState.Pagination(
            newPageThreshold = 10
        ) {
            dispatch(HeroesListView.UiEvent.NewPageRequired)
        }
    }

    @Composable
    private fun LazyListState.Pagination(
        newPageThreshold: Int,
        onNewPageRequired: () -> Unit,
    ) {
        LaunchedEffect(
            key1 = newPageThreshold,
            key2 = onNewPageRequired,
        ) {
            snapshotFlow { this@Pagination }
                .filter { state ->
                    val lastVisibleItem = state.layoutInfo.visibleItemsInfo.lastOrNull() ?: return@filter false

                    state.layoutInfo.totalItemsCount - lastVisibleItem.index <= newPageThreshold
                }
                .onEach {
                    onNewPageRequired.invoke()
                }
                .collect()
        }
    }
}
