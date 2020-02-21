package com.arttttt.swapisamplemvi.ui.herodetails

import android.content.Context
import android.view.Gravity
import android.view.View
import androidx.core.app.SharedElementCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.transition.*
import com.arttttt.swapisamplemvi.R
import com.arttttt.swapisamplemvi.ui.base.BaseFragment
import com.arttttt.swapisamplemvi.ui.base.UiAction
import com.badoo.mvicore.android.AndroidBindings
import com.badoo.mvicore.modelWatcher
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_hero_details.*
import javax.inject.Inject

class HeroDetailsFragment: BaseFragment<HeroDetailsFragment.HeroDetailsUiAction, HeroDetailsViewModel>(R.layout.fragment_hero_details) {

    companion object {
        const val HERO_NAME = "HERO_NAME"

        fun newInstance(name: String): Fragment {
            return HeroDetailsFragment().apply {
                arguments = bundleOf(
                    HERO_NAME to name
                )
            }
        }
    }

    sealed class HeroDetailsUiAction: UiAction {
        object BackPressed: HeroDetailsUiAction()
    }

    @Inject
    override lateinit var binder: AndroidBindings<BaseFragment<HeroDetailsUiAction, HeroDetailsViewModel>>

    override fun onViewCreated() {
        super.onViewCreated()

        tvHeroName.transitionName = argument(HERO_NAME)

        setEnterSharedElementCallback(object: SharedElementCallback() {
            override fun onMapSharedElements(
                names: MutableList<String>,
                sharedElements: MutableMap<String, View>
            ) {
                sharedElements[names.first()] = tvHeroName
            }
        })

        val watcher = modelWatcher<HeroDetailsViewModel> {
            (HeroDetailsViewModel::name or HeroDetailsViewModel::birthDate) { viewModel ->
                tvHeroName.text = viewModel.name
                tvHeroBirthDate.text = viewModel.birthDate
            }
        }

        states
            .subscribe(watcher::invoke)
            .add()
    }

    override fun onBackPressed() {
        Observable
            .just(HeroDetailsUiAction.BackPressed)
            .emitUiAction()
    }
}
