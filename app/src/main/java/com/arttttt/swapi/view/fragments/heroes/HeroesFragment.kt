package com.arttttt.swapi.view.fragments.heroes

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arttttt.swapi.view.activities.herodetails.HeroDetailsActivity
import com.arttttt.swapi.R
import com.arttttt.swapi.model.hero.Hero
import com.arttttt.swapi.presenter.heroes.HeroesContract
import com.arttttt.swapi.utils.ActivityUtils
import com.arttttt.swapi.view.fragments.base.BaseFragment
import com.arttttt.swapi.view.fragments.heroes.adapter.HeroesAdapter
import com.jakewharton.rxbinding2.widget.textChanges
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_hero.*
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

class HeroesFragment: BaseFragment<HeroesContract.View, HeroesContract.Presenter>(), HeroesContract.View {

    private val debounceTimeout = 500L

    override fun getLayoutResource() = R.layout.fragment_hero
    override fun getMvpView() = this

    override val presenter: HeroesContract.Presenter by inject()

    private lateinit var heroesAdapter: HeroesAdapter

    override fun initializeUI(view: View) {
        heroRecycler.layoutManager = LinearLayoutManager(context)
        heroesAdapter = HeroesAdapter { hero, sharedViewId, position ->
            heroRecycler
                .layoutManager
                ?.findViewByPosition(position)
                ?.findViewById<View>(sharedViewId)
                ?.let { view ->
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity!!,
                        view,
                        "hero_name"
                    )

                    val extra = Bundle().apply {
                        putParcelable(HeroDetailsActivity.HeroExtra, hero)
                    }

                    ActivityUtils.startActivity<HeroDetailsActivity>(context!!,
                        options.toBundle(),
                        extra,
                        0)
                }
        }
        heroRecycler.adapter = heroesAdapter

        presenter.initialize()

        searchHeroEdit.textChanges()
            .skipInitialValue()
            .debounce(debounceTimeout, TimeUnit.MILLISECONDS)
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { presenter.findHero(it.toString()) }
            .apply { compositeDisposable.add(this) }
    }

    override fun hideMessage() {
        messageTextView.visibility = View.GONE
    }

    override fun showHeroes(heroes: List<Hero>) {
        heroesAdapter.updateList(heroes)
    }

    override fun showLoading(show: Boolean) {
        if (show) loadingIndicator.show() else loadingIndicator.hide()
    }

    override fun showMessage(message: String) {
        messageTextView.visibility = View.VISIBLE

        messageTextView.text = message
    }
}