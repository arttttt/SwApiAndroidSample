package com.arttttt.heroeslist.ui.list.view

import androidx.compose.runtime.Composable
import com.arkivanov.mvikotlin.core.view.MviView
import com.arttttt.heroeslist.domain.entity.Hero

internal interface HeroesListView : MviView<HeroesListView.Model, HeroesListView.UiEvent> {

    data class Model(
        val items: List<Hero>
    )

    sealed class UiEvent {
        data class HeroClicked(val id: Int) : UiEvent()
    }

    sealed class Command {
        data class ShowToast(val id: Int) : Command()
    }

    fun handleCommand(command: Command)

    @Composable
    fun Content()
}
