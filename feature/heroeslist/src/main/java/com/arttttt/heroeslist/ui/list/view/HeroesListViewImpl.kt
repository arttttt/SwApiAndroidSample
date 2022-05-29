package com.arttttt.heroeslist.ui.list.view

import android.os.LocaleList
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.arkivanov.mvikotlin.core.view.BaseMviView
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

internal class HeroesListViewImpl : BaseMviView<HeroesListView.Model, HeroesListView.UiEvent>(), HeroesListView {

    private val states = MutableStateFlow(
        value = HeroesListView.Model(
            items = emptyList()
        )
    )

    private val commands = MutableSharedFlow<HeroesListView.Command?>(extraBufferCapacity = 1)

    override fun render(model: HeroesListView.Model) {
        states.value = model
    }

    override fun handleCommand(command: HeroesListView.Command) {
        commands.tryEmit(command)
    }

    @Composable
    override fun Content() {
        val state by states.collectAsState()
        val command by commands.collectAsState(initial = null)

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            if (state.items.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier.fillParentMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            items(
                items = state.items,
                key = { hero -> hero.id },
                itemContent = { hero ->
                    Row(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .clickable { dispatch(HeroesListView.UiEvent.HeroClicked(hero.id)) }
                            .padding(8.dp)
                    ) {
                        Text(text = hero.name)
                    }
                }
            )
        }

        HandleCommandInternal(command)
    }

    @Composable
    fun HandleCommandInternal(command: HeroesListView.Command?) {
        when (command) {
            is HeroesListView.Command.ShowToast -> Toast.makeText(LocalContext.current, command.id.toString(), Toast.LENGTH_SHORT).show()
            else -> {}
        }
    }
}
