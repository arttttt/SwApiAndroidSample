package com.arttttt.main.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.alphicc.brick.AndroidScreensContainer
import com.alphicc.brick.Screen
import com.alphicc.brick.TreeRouter
import com.arttttt.main.MainFeatureComponentHolder
import com.arttttt.main.ui.theme.MVISampleComposeTheme
import javax.inject.Inject

internal class MainActivity : ComponentActivity() {

    @Inject
    lateinit var router: TreeRouter
    @Inject
    lateinit var rootScreen: Screen<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        MainFeatureComponentHolder
            .component
            .inject(this)

        super.onCreate(savedInstanceState)

        setContent {
            MainContent(
                router = router,
                rootScreen = rootScreen
            )
        }
    }
}
