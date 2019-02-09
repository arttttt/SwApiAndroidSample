package com.arttttt.swapi.view.activities.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.arttttt.swapi.R
import com.arttttt.swapi.presenter.main.MainContract
import com.arttttt.swapi.view.fragments.heroes.HeroesFragment
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MainContract.View {

    private val presenter: MainContract.Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.attachView(this)
        presenter.initialize()
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.detachView()
    }

    override fun initializeFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, HeroesFragment())
            .commit()
    }
}
