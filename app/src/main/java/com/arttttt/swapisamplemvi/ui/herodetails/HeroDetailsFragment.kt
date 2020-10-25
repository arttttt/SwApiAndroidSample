package com.arttttt.swapisamplemvi.ui.herodetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.arttttt.swapisamplemvi.R
import com.arttttt.swapisamplemvi.ui.AppActivity
import com.arttttt.swapisamplemvi.ui.base.BaseBindings
import com.arttttt.swapisamplemvi.ui.base.BaseFragment
import com.arttttt.swapisamplemvi.ui.base.UiAction
import com.arttttt.swapisamplemvi.ui.base.UiActionsDelegate
import com.arttttt.swapisamplemvi.ui.herodetails.di.DaggerHeroDetailsComponent
import com.arttttt.swapisamplemvi.utils.extensions.toObservable
import com.badoo.mvicore.ModelWatcher
import com.badoo.mvicore.modelWatcher
import kotlinx.android.synthetic.main.fragment_hero_details.*
import javax.inject.Inject
import javax.inject.Provider

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

    @Inject
    lateinit var uiActionsProvider: Provider<HeroDetailsUiActionsDelegate>

    @Inject
    lateinit var bindingsProvider: Provider<HeroDetailsBindings>

    override fun provideBindings(): BaseBindings<out BaseFragment<Action, ViewModel>> {
        return bindingsProvider.get()
    }

    override fun provideUiActions(): UiActionsDelegate<Action> {
        return uiActionsProvider.get()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerHeroDetailsComponent.factory().create((requireActivity() as AppActivity).component).inject(this)
        super.onCreate(savedInstanceState)
    }

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
