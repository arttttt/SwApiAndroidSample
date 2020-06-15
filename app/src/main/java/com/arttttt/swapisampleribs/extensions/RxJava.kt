package com.arttttt.swapisampleribs.extensions

import io.reactivex.Observable

fun<T> T.toObservable(): Observable<T> {
    return if (this != null) {
        return Observable.just(this)
    } else {
        Observable.empty()
    }
}