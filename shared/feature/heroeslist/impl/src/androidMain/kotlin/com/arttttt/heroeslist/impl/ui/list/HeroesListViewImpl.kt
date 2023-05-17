package com.arttttt.heroeslist.impl.ui.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.arttttt.heroeslist.impl.ui.list.lazylist.HeroLazyListDelegate
import com.arttttt.heroeslist.impl.ui.list.lazylist.ProgressAdapterDelegate
import com.arttttt.arch.view.AndroidAbstractComponentView
import com.arttttt.arch.view.ListItem
import com.arttttt.arch.view.lazylist.dsl.rememberLazyListDelegateManager
import com.arttttt.heroeslist.api.components.HeroInfoComponent
import com.arttttt.heroeslist.api.components.HeroesListComponent
import com.arttttt.heroeslist.api.ui.list.HeroesListView
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach

class HeroesListViewImpl(
    private val component: HeroesListComponent
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

        Column(
            modifier = modifier,
        ) {

            component.toolbarComponent.view.Content(Modifier)

            ContentList(
                items = model.items
            )
        }

        val dialogs = component.dialogSlot.subscribeAsState()
        dialogs.value.child?.instance?.ShowDialog()
    }

    @Composable
    private fun HeroInfoComponent.ShowDialog() {
        AlertDialog(
            title = {
                Text(
                    text = title,
                )
            },
            text = {
                Text(
                    text = message,
                )
            },
            onDismissRequest = {
                onDismissed()
            },
            confirmButton = {
                Button(
                    onClick = {
                        onDismissed()
                    },
                ) {
                    Text(
                        text = "OK",
                    )
                }
            },
        )
    }

    @Composable
    private fun ColumnScope.ContentList(
        items: ImmutableList<ListItem>
    ) {
        val lazyListState = rememberLazyListState()

        val lazyListDelegatesManager = rememberLazyListDelegateManager(
            delegates = persistentListOf(
                HeroLazyListDelegate(
                    onClick = { name ->
                        dispatch(HeroesListView.UiEvent.HeroClicked(name))
                    },
                    onLongPressed = { name ->
                        dispatch(HeroesListView.UiEvent.ShowInfoClicked(name))
                    }
                ),
                ProgressAdapterDelegate(),
            ),
        )

        LazyColumn(
            state = lazyListState,
            modifier = Modifier.fillMaxSize(),
        ) {

            items(
                items = items,
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
