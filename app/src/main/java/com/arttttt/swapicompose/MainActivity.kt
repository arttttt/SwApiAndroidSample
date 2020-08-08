package com.arttttt.swapicompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Box
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope.weight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.sp
import com.arttttt.swapicompose.data.repository.DefaultSwRepository
import com.arttttt.swapicompose.domain.feature.heroesfeature.HeroesFeature
import com.arttttt.swapicompose.ui.AppBottomNavigation
import com.arttttt.swapicompose.ui.base.SwApiComposeTheme
import com.badoo.mvicore.binder.Binder
import com.badoo.mvicore.binder.lifecycle.ManualLifecycle
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    companion object {
        private const val REMAINING_ITEMS_THRESHOLD = 5
    }

    data class ViewModel(
        val isLoading: Boolean,
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
            isLoading = false
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.plant(Timber.DebugTree())

        setContent {
            myApp()
        }

        val lifecycle = ManualLifecycle()
        Binder(
            lifecycle = lifecycle
        ).apply {
            bind(heroesFeature to Consumer { state ->
                this@MainActivity.state.value = ViewModel(
                    items = state.heroes.map { hero ->
                        hero.name
                    },
                    isLoading = state.isLoading || state.isLoadingMore
                )
            })
        }

        lifecycle.begin()
    }

    @Composable
    private fun myApp() {
        SwApiComposeTheme {
            Scaffold(
                bodyContent = {
                    Column {
                        content()
                        AppBottomNavigation()
                    }
                }
            )
        }
    }

    @Composable
    private fun content() {
        TopAppBar {
            Text(
                modifier = Modifier.gravity(Alignment.CenterVertically),
                text = "HELLO",
                fontSize = 20.sp
            )
        }

        Box(modifier = Modifier.fillMaxWidth().weight(1f)) {
            LazyColumnForIndexed(
                items = state.value.items,
                itemContent = { index, it ->
                    Box(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .clickable(
                                onClick = {
                                    Timber.e(it)
                                }
                            ),
                        children = {
                            Text(
                                text = it,
                                //modifier = Modifier.padding(16.dp)
                            )
                        }
                    )

                    if (state.value.isLoading && index == state.value.items.size - 1) {
                        Text(
                            text = "LOADING...",
                            color = Color.Red,
                            fontSize = 30.sp
                            //modifier = Modifier.padding(16.dp)
                        )
                    }

                    if (index >= state.value.items.size - REMAINING_ITEMS_THRESHOLD - 1) {
                        heroesFeature.accept(HeroesFeature.Wish.LoadMoreHeroes)
                    }
                }
            )
        }
    }
}