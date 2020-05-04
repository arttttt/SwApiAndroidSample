package com.arttttt.swapisamplemvi.ui.herodetails

import androidx.fragment.app.Fragment
import com.arttttt.swapisamplemvi.R
import com.arttttt.swapisamplemvi.ui.base.BaseFragment
import com.arttttt.swapisamplemvi.ui.base.UiAction
import com.arttttt.swapisamplemvi.utils.extensions.toObservable
import com.badoo.mvicore.ModelWatcher
import com.badoo.mvicore.modelWatcher
import kotlinx.android.synthetic.main.fragment_hero_details.*

class HeroDetailsFragment: BaseFragment<HeroDetailsFragment.Action, HeroDetailsFragment.ViewModel>(R.layout.fragment_hero_details) {

    companion object {
        fun newInstance(): Fragment {
            return HeroDetailsFragment()
        }
    }

    sealed class Action: UiAction {
        object BackPressed: Action()
    }

    data class ViewModel(
        val name: String,
        val birthDate: String
    ): com.arttttt.swapisamplemvi.ui.base.ViewModel

    override fun onViewPreCreated() {
        super.onViewPreCreated()

        postponeEnterTransition()
    }

    override fun onBackPressed() {
        Action.BackPressed
            .toObservable()
            .bindUiAction()
    }

    override fun getModelWatcher(): ModelWatcher<ViewModel> {
        return modelWatcher {
            (ViewModel::name or ViewModel::birthDate) { viewModel ->
                tvHeroName.transitionName = viewModel.name
                tvHeroName.text = viewModel.name
                tvHeroBirthDate.text = viewModel.birthDate

                startPostponedEnterTransition()
            }
        }
    }
}
