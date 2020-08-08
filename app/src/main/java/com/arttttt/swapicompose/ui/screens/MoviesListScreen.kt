package com.arttttt.swapicompose.ui.screens

import androidx.compose.foundation.Box
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.ColumnScope.gravity
import androidx.compose.foundation.layout.RowScope.gravity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun MoviesListScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        gravity = Alignment.Center,
        children = {
            Text(
                text = "MOVIES IS UNDER CONSTRUCTION",
                fontSize = 24.sp
            )
        }
    )
}