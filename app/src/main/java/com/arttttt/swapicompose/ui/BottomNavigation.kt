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
fun AppBottomNavigation(currentTab: Tab = Tab.HEROES, onTabSelected: (Tab) -> Unit) {
    BottomNavigation {
        enumValues<Tab>().forEach { tab ->
            BottomNavigationItem(
                    icon = {},
                    label = { Text(text = tab.title) },
                    selected = currentTab == tab,
                    selectedContentColor = Color.Red,
                    unselectedContentColor = Color.Black,
                    onSelect = { onTabSelected.invoke(tab) }
            )
        }
    }
}