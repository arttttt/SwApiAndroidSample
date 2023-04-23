package com.arttttt.androidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import com.arttttt.app.root.RootComponentImpl

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootComponent = RootComponentImpl(
            componentContext = defaultComponentContext(),
        )

        setContent {
            MaterialTheme {
                rootComponent.view.Content(modifier = Modifier)
            }
        }
    }
}
