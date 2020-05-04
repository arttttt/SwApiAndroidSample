@file:Suppress("NOTHING_TO_INLINE")

package com.arttttt.swapisamplemvi.utils.extensions

inline fun<T> T.isNotNull(): Boolean {
    return this != null
}

inline fun<T> T.isNull(): Boolean {
    return this == null
}

inline fun<reified T>Any?.castTo(): T? {
    return this as? T
}

inline fun<reified T>Any.unsafeCastTo(): T {
    return this as T
}

inline fun invokeIf(predicate: () -> Boolean, block: () -> Unit) {
    if (predicate()) {
        block()
    }
}

inline fun invokeIfNot(predicate: () -> Boolean, block: () -> Unit) {
    if (!predicate()) {
        block()
    }
}