package com.arttttt.swapi.view.activities.herodetails

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.arttttt.swapi.R
import com.arttttt.swapi.model.hero.Hero
import com.arttttt.swapi.presenter.herodetails.HeroDetailsContract
import kotlinx.android.synthetic.main.activity_hero_details.*
import org.koin.android.ext.android.inject

class HeroDetailsActivity : AppCompatActivity(), HeroDetailsContract.View {

    companion object {
        const val HeroExtra = "HERO_EXTRA"
    }

    private val presenter: HeroDetailsContract.Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_details)

        presenter.attachView(this)
        presenter.initialize()
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.detachView()
    }

    override fun initializeUI() {
        intent?.let {
            val hero = it.getParcelableExtra<Hero>(HeroExtra)
            name.text = hero.name
            height.text = hero.height
            mass.text = hero.mass
            hair_color.text = hero.hairColor
            skin_color.text = hero.skinColor
            eye_color.text = hero.eyeColor
            birth_year.text = hero.birthYear
            gender.text = hero.gender
        }
    }
}
