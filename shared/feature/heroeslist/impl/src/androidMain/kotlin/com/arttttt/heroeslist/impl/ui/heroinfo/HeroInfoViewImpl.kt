package com.arttttt.heroeslist.impl.ui.heroinfo

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arttttt.arch.view.AndroidAbstractComponentView

internal actual class HeroInfoViewImpl actual constructor() :
    AndroidAbstractComponentView<HeroInfoView.Model, HeroInfoView.UiEvent>(),
    HeroInfoView {

    override val initialState: HeroInfoView.Model = HeroInfoView.Model(
        title = "",
        message = "",
    )

    @Composable
    override fun Content(
        model: HeroInfoView.Model,
        modifier: Modifier,
    ) {
        AlertDialog(
            title = {
                Text(
                    text = model.title,
                )
            },
            text = {
                Text(
                    text = model.message,
                )
            },
            onDismissRequest = {
                dispatch(HeroInfoView.UiEvent.Dismissed)
            },
            confirmButton = {
                Button(
                    onClick = {
                        dispatch(HeroInfoView.UiEvent.Dismissed)
                    },
                ) {
                    Text(
                        text = "OK",
                    )
                }
            },
        )
    }
}
