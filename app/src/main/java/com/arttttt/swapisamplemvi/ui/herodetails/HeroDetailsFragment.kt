package com.arttttt.swapisamplemvi.ui.herodetails

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.arttttt.swapisamplemvi.R
import com.arttttt.swapisamplemvi.feature.herofeature.HeroFeature
import com.arttttt.swapisamplemvi.ui.RootCoordinator
import com.arttttt.swapisamplemvi.ui.base.BaseFragment
import com.arttttt.swapisamplemvi.ui.base.UiAction
import com.arttttt.swapisamplemvi.ui.heroeslist.HeroesListFragment
import com.arttttt.swapisamplemvi.utils.extensions.unsafeCastTo
import com.badoo.mvicore.android.AndroidBindings
import com.badoo.mvicore.byValue
import com.badoo.mvicore.modelWatcher
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_hero_details.*
import org.koin.android.ext.android.get
import org.koin.core.qualifier.named

class HeroDetailsFragment: BaseFragment<HeroDetailsFragment.HeroDetailsUiAction, HeroDetailsViewModel>(R.layout.fragment_hero_details) {

    companion object {
        private const val HERO_NAME = "HERO_NAME"

        fun newInstance(name: String): Fragment {
            return HeroDetailsFragment().apply {
                arguments = bundleOf(
                    HERO_NAME to name
                )
            }
        }
    }

    sealed class HeroDetailsUiAction: UiAction {
        class FragmentCreated(val name: String): HeroDetailsUiAction()
        object BackPressed: HeroDetailsUiAction()
    }

    override val binder: AndroidBindings<BaseFragment<HeroDetailsUiAction, HeroDetailsViewModel>> = HeroDetailsBinding(
        lifecycleOwner = this,
        rootCoordinator = get(named<RootCoordinator>()),
        heroFeature = HeroFeature(
            swRepository = get()
        )
    ).unsafeCastTo()

    override fun onViewCreated() {
        super.onViewCreated()

        Observable
            .just(HeroDetailsUiAction.FragmentCreated(argument(HERO_NAME)))
            .emitUiAction()

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
