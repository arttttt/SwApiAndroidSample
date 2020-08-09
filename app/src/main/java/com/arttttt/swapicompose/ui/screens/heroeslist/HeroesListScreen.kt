package com.arttttt.swapicompose.ui.screens.heroeslist

import androidx.compose.foundation.Box
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ColumnScope.weight
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arttttt.swapicompose.BuildConfig
import com.arttttt.swapicompose.data.repository.DefaultSwRepository
import com.arttttt.swapicompose.domain.feature.heroesfeature.HeroesFeature
import com.badoo.mvicore.binder.Binder
import com.badoo.mvicore.binder.lifecycle.ManualLifecycle
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import timber.log.Timber

private const val REMAINING_ITEMS_THRESHOLD = 5

private data class ViewModel(
    val isLoading: Boolean,
    val isLoadingMore: Boolean,
    val items: List<String>
)

private val heroesFeature by lazy {
    HeroesFeature(
        swRepository = DefaultSwRepository(
            swApi = Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create()
        )
    )
}

private val state = mutableStateOf(
    value = ViewModel(
        items = emptyList(),
        isLoading = false,
        isLoadingMore = false
    )
)

@Composable
fun HeroesListScreen() {
    val lifecycle = ManualLifecycle()
    Binder(
        lifecycle = lifecycle
    ).apply {
        bind(heroesFeature to Consumer { heroesFeatureState ->
            state.value = ViewModel(
                items = heroesFeatureState.heroes.map { hero ->
                    hero.name
                },
                isLoading = heroesFeatureState.isLoading,
                isLoadingMore = heroesFeatureState.isLoadingMore
            )
        })
    }

    lifecycle.begin()

    showLoadingIndicatorIfNeeded()

    Box(modifier = Modifier.fillMaxWidth().weight(1f)) {
        LazyColumnForIndexed(
            items = state.value.items,
            itemContent = { index, title ->
                listItem(
                    title = title,
                    onClick = { Timber.e(title) }
                )

                addLoadingMoreIndicatorIfNeeded(index = index)

                checkLoadMore(
                    index = index,
                    onLoadMore = { heroesFeature.accept(HeroesFeature.Wish.LoadMoreHeroes) }
                )
            }
        )
    }
}

@Composable
private fun showLoadingIndicatorIfNeeded() {
    if (state.value.isLoading) {
        Box(
            gravity = Alignment.Center,
            modifier = Modifier.fillMaxSize(),
            children = { CircularProgressIndicator() }
        )
    }
}

@Composable
private fun LazyItemScope.listItem(title: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillParentMaxWidth()
            .clickable(
                onClick = onClick
            ),
        children = {
            Text(
                text = title,
                //modifier = Modifier.padding(16.dp)
            )
        }
    )
}

@Composable
private fun LazyItemScope.addLoadingMoreIndicatorIfNeeded(index: Int) {
    if (state.value.isLoadingMore && index == state.value.items.size - 1) {
        Box(
            gravity = Alignment.Center,
            modifier = Modifier.fillParentMaxWidth(),
            children = {
                Row(
                    verticalGravity = Alignment.CenterVertically,
                    children = {
                        Text(text = "Loading")
                        CircularProgressIndicator(
                            modifier = Modifier.size(
                                width = 24.dp,
                                height = 24.dp
                            ),
                            strokeWidth = 2.dp
                        )
                    }
                )
            }
        )
    }
}

@Composable
private fun checkLoadMore(index: Int, onLoadMore: () -> Unit) {
    if (index >= state.value.items.size - REMAINING_ITEMS_THRESHOLD - 1) {
        onLoadMore.invoke()
    }
}