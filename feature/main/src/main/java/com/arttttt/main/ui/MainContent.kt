package com.arttttt.main.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.alphicc.brick.AndroidScreensContainer
import com.alphicc.brick.Screen
import com.alphicc.brick.TreeRouter
import com.arkivanov.essenty.instancekeeper.instanceKeeper
import com.arkivanov.essenty.statekeeper.stateKeeper
import com.arttttt.main.ui.theme.MVISampleComposeTheme

@Composable
internal fun MainContent(
    router: TreeRouter,
    rootScreen: Screen<*>
) {
    MVISampleComposeTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            AndroidScreensContainer(router)

            router.newRootScreen(
                rootScreen,
                LocalSavedStateRegistryOwner.current.stateKeeper() to LocalViewModelStoreOwner.current!!.instanceKeeper()
            )
        }
    }
}
