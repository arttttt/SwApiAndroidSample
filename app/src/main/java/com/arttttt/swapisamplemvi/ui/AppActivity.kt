package com.arttttt.swapisamplemvi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arttttt.swapisamplemvi.R
import com.arttttt.swapisamplemvi.ui.heroeslist.HeroesListFragment
import com.arttttt.swapisamplemvi.utils.extensions.isNull

class AppActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_activity)

        if (savedInstanceState.isNull()) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.rootContainer, HeroesListFragment())
                .commit()
        }
    }
}