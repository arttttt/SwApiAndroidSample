package com.arttttt.swapisamplemvi.ui.herodetails

import android.content.Context
import android.view.View
import androidx.core.app.SharedElementCallback
import androidx.fragment.app.Fragment
import com.arttttt.swapisamplemvi.R
import com.arttttt.swapisamplemvi.ui.base.BaseBindings
import com.arttttt.swapisamplemvi.ui.base.BaseFragment
import com.arttttt.swapisamplemvi.ui.base.UiAction
import com.badoo.mvicore.ModelWatcher
import com.badoo.mvicore.modelWatcher
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_hero_details.*
import javax.inject.Inject

class HeroDetailsFragment: BaseFragment<HeroDetailsFragment.HeroDetailsUiAction, HeroDetailsViewModel>(R.layout.fragment_hero_details) {

    companion object {
        fun newInstance(): Fragment {
            return HeroDetailsFragment()
        }
    }

    sealed class HeroDetailsUiAction: UiAction {
        object BackPressed: HeroDetailsUiAction()
    }

    @Inject
    override lateinit var binder: BaseBindings<BaseFragment<HeroDetailsUiAction, HeroDetailsViewModel>>

    override fun onAttach(context: Context) {
        super.onAttach(context)

        setEnterSharedElementCallback(object: SharedElementCallback() {
            override fun onMapSharedElements(
                names: MutableList<String>,
                sharedElements: MutableMap<String, View>
            ) {
                sharedElements[names.first()] = tvHeroName
            }
        })
    }

    override fun onViewPreCreated() {
        super.onViewPreCreated()

        postponeEnterTransition()
    }

    override fun onBackPressed() {
        Observable
            .just(HeroDetailsUiAction.BackPressed)
            .emitUiAction()
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
