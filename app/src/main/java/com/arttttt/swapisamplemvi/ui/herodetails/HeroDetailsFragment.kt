package com.arttttt.swapisamplemvi.ui.herodetails

import androidx.fragment.app.Fragment
import com.arttttt.swapisamplemvi.R
import com.arttttt.swapisamplemvi.ui.base.BaseBindings
import com.arttttt.swapisamplemvi.ui.base.BaseFragment
import com.arttttt.swapisamplemvi.ui.base.UiAction
import com.badoo.mvicore.ModelWatcher
import com.badoo.mvicore.modelWatcher
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_hero_details.*

class HeroDetailsFragment: BaseFragment<HeroDetailsFragment.HeroDetailsUiAction, HeroDetailsViewModel>(R.layout.fragment_hero_details) {

    companion object {
        fun newInstance(): Fragment {
            return HeroDetailsFragment()
        }
    }

    sealed class HeroDetailsUiAction: UiAction {
        object BackPressed: HeroDetailsUiAction()
    }

    override fun onViewPreCreated() {
        super.onViewPreCreated()

        postponeEnterTransition()
    }

    override fun onBackPressed() {
        Observable
            .just(HeroDetailsUiAction.BackPressed)
            .bindUiAction()
    }

    override fun getModelWatcher(): ModelWatcher<HeroDetailsViewModel> {
        return modelWatcher {
            (HeroDetailsViewModel::name or HeroDetailsViewModel::birthDate) { viewModel ->
                tvHeroName.transitionName = viewModel.name
                tvHeroName.text = viewModel.name
                tvHeroBirthDate.text = viewModel.birthDate

                startPostponedEnterTransition()
            }
        }
    }
}
