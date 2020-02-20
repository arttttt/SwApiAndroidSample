package com.arttttt.swapisamplemvi.utils.extensions

import androidx.fragment.app.Fragment

inline fun<reified T> Fragment.argument(key: String, defValue: T? = null): T {
    return (arguments?.get(key) ?: defValue) as T
}