package com.arttttt.hero.impl.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import com.arttttt.arch.view.AndroidAbstractComponentView
import com.arttttt.hero.api.HeroView

class HeroViewImpl : AndroidAbstractComponentView<HeroView.Model, HeroView.UiEvent>(),
    HeroView {

    override val initialState: HeroView.Model = HeroView.Model(
        title = "",
    )

    @Composable
    override fun Content(model: HeroView.Model, modifier: Modifier) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = { dispatch(HeroView.UiEvent.BackPressed) }
                    ) {
                        Icon(
                            painter = rememberVectorPainter(Icons.Default.ArrowBack),
                            contentDescription = null,
                        )
                    }
                },
                title = {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = model.title,
                    )
                }
            )

            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                text = model.title,
                textAlign = TextAlign.Center,
            )
        }
    }
}
