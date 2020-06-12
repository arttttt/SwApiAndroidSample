package com.arttttt.swapisampleribs.app

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.ViewGroup
import com.arttttt.swapisampleribs.R
import com.arttttt.swapisampleribs.rib.container.Container
import com.arttttt.swapisampleribs.rib.container.ContainerBuilder
import com.badoo.ribs.android.RibActivity
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.builder.BuildContext
import kotlinx.android.synthetic.main.activity_app.*

class AppActivity: RibActivity() {
    override val rootViewGroup: ViewGroup
        get() = content

    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
        setContentView(R.layout.activity_app)
        super.onCreate(savedInstanceState)
    }

    override fun createRib(savedInstanceState: Bundle?): Rib {
        return ContainerBuilder(
            object : Container.Dependency {}
        ).build(BuildContext.root(savedInstanceState))
    }
}