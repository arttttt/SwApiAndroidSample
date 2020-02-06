@file:Suppress("NOTHING_TO_INLINE")

package com.arttttt.swapisamplemvi.utils.extensions

inline fun<T> T.isNull(): Boolean {
    return this == null
}