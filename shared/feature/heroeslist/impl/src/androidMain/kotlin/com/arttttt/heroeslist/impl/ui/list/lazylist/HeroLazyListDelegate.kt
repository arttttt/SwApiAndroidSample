package com.arttttt.heroeslist.impl.ui.list.lazylist

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.arttttt.arch.view.lazylist.dsl.lazyListDelegate
import com.arttttt.heroeslist.api.ui.list.models.HeroListItem
import kotlinx.coroutines.launch

internal fun HeroLazyListDelegate(
    onClick: (String) -> Unit,
    onLongPressed: (String) -> Unit,
) = lazyListDelegate<HeroListItem>(
    key = { item -> item.name },
) {

    val pressScope = rememberCoroutineScope()
    val interactionSource = remember {
        MutableInteractionSource()
    }

    Text(
        modifier = Modifier
            .fillParentMaxWidth()
            .indication(interactionSource, LocalIndication.current)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        onClick.invoke(item.name)
                    },
                    onLongPress = {
                        onLongPressed.invoke(item.name)
                    },
                    onPress = { offset ->
                        val press = PressInteraction.Press(offset)
                        pressScope.launch { interactionSource.emit(press) }
                        awaitRelease()
                        interactionSource.emit(PressInteraction.Release(press))
                    }
                )
            }
            .padding(
                vertical = 16.dp,
                horizontal = 8.dp,
            ),
        text = item.name,
    )
}
