package com.arttttt.swapisamplemvi.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.badoo.mvicore.android.AndroidBindings
import com.badoo.mvicore.android.AndroidTimeCapsule

abstract class BaseFragment<MviViewController: ViewController<out UiEvent, out ViewModel>>: Fragment(), IBackHandler {
    abstract val binder: AndroidBindings<MviViewController>
    abstract val viewController: MviViewController
    protected lateinit var timeCapsule: AndroidTimeCapsule

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return viewController.createView(inflater, container).apply { timeCapsule = AndroidTimeCapsule(savedInstanceState) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binder.setup(viewController)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        timeCapsule.saveState(outState)
    }
}
