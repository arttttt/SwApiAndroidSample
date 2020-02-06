package com.arttttt.swapisamplemvi.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.badoo.mvicore.android.AndroidBindings

abstract class BaseFragment<MvCoreView: MviView<out UiEvent, out ViewModel>>: Fragment() {
    abstract val binder: AndroidBindings<MvCoreView>
    abstract val mviView: MvCoreView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return mviView.createView(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binder.setup(mviView)
    }
}
