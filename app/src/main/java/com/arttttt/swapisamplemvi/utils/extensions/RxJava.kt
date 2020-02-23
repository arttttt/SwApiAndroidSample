package com.arttttt.swapisamplemvi.utils.extensions

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

fun<T> T?.toObservable(): Observable<T> {
    return if (this.isNull()) {
        Observable.empty()
    } else {
        Observable.just(this)
    }
}

fun PublishSubject<Unit>.onNext() {
    onNext(Unit)
}