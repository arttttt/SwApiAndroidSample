package com.arttttt.main.ui

import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.arttttt.main.MainFeatureComponentHolder
import com.arttttt.main.R
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

internal class MainActivity : AppCompatActivity() {

    companion object {
        private val component = MainFeatureComponentHolder.getComponent()
    }

    @Inject
    lateinit var coordinator: MainCoordinator

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator by lazy(LazyThreadSafetyMode.NONE) {
        AppNavigator(
            activity = this,
            containerId = R.id.fragment_container
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        MainFeatureComponentHolder
            .getComponent()
            .inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        savedInstanceState ?: coordinator.start()

        onBackPressedDispatcher.addCallback(
            owner = this
        ) {
            coordinator.back()
        }
    }

    override fun onResume() {
        super.onResume()

        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()

        navigatorHolder.removeNavigator()
    }

}
