package com.arttttt.swapicompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Box
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.state
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arttttt.swapicompose.ui.AppBottomNavigation
import com.arttttt.swapicompose.ui.Tab
import com.arttttt.swapicompose.ui.base.SwApiComposeTheme
import com.arttttt.swapicompose.ui.screens.heroeslist.HeroesListScreen
import com.arttttt.swapicompose.ui.screens.movieslist.MoviesListScreen
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.plant(Timber.DebugTree())

        setContent {
            myApp()
        }
    }

    @Composable
    private fun myApp() {
        SwApiComposeTheme {
            Scaffold(
                bodyContent = { content() }
            )
        }
    }

    @Composable
    private fun content() {
        val selectedTab = state { Tab.HEROES }

        Column {
            appBar(tab = selectedTab.value)

            Box(
                modifier = Modifier.weight(1f),
                children = { currentTab(tab = selectedTab.value) }
            )

            AppBottomNavigation(
                currentTab = selectedTab.value,
                onTabSelected = { tab -> selectedTab.value = tab }
            )
        }
    }

    @Composable
    private fun currentTab(tab: Tab) {
        when (tab) {
            Tab.HEROES -> HeroesListScreen()
            Tab.MOVIES -> MoviesListScreen()
        }
    }

    @Composable
    private fun appBar(tab: Tab) {
        TopAppBar {
            Text(
                modifier = Modifier
                    .gravity(Alignment.CenterVertically)
                    .padding(
                        start = 16.dp,
                        end = 16.dp
                    ),
                text = when (tab) {
                    Tab.HEROES -> getString(R.string.heroes)
                    Tab.MOVIES -> getString(R.string.movies)
                },
                fontSize = 20.sp
            )
        }
    }
}