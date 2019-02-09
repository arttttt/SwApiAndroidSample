package com.arttttt.swapi.presenter.heroes

import com.arttttt.swapi.model.hero.Hero
import com.arttttt.swapi.model.hero.repository.HeroRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HeroesPresenter(private val heroRepository: HeroRepository): HeroesContract.Presenter {
    override var mView: HeroesContract.View? = null

    override fun initialize() {
        mView?.let { view ->
            heroRepository.getAllHeroes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.showLoading(true) }
                .doFinally { view.showLoading(false) }
                .subscribe({ heroes ->
                    view.showHeroes(heroes)
                }, {
                    it.message?.let { message ->
                        view.showMessage(message)
                    }
                })
        }
    }

    override fun findHero(name: String) {
        mView?.let { view ->
            heroRepository.getHero(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.showLoading(true) }
                .doFinally { view.showLoading(false) }
                .subscribe({ heroes ->
                    view.hideMessage()
                    view.showHeroes(heroes)
                }, {
                    it.message?.let { message ->
                        view.showMessage(message)
                    }
                })
        }
    }

    override fun openHeroDetails(sharedViewId: Int, position: Int, hero: Hero) {
        mView?.let { view ->
            view.startDetailsActivity(sharedViewId, position, hero)
        }
    }
}