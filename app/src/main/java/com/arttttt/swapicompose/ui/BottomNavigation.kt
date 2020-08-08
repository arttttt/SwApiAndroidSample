package com.arttttt.swapicompose.ui

import androidx.compose.foundation.Text
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.state
import androidx.compose.ui.graphics.Color

enum class Tab(val title: String) {
    HEROES("Heroes"),
    MOVIES("Movies")
}

@Composable
fun AppBottomNavigation(defaultTab: Tab = Tab.HEROES) {
    val selectedTab = state { defaultTab }

    BottomNavigation {
        enumValues<Tab>().forEach { tab ->
            BottomNavigationItem(
                    icon = {},
                    label = { Text(text = tab.title) },
                    selected = selectedTab.value == tab,
                    selectedContentColor = Color.Red,
                    unselectedContentColor = Color.Black,
                    onSelect = { selectedTab.value = tab }
            )
        }
    }
}