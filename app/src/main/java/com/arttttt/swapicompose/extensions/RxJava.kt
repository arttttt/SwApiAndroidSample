package com.arttttt.swapicompose.extensions

import io.reactivex.Observable
import io.reactivex.ObservableSource
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

fun<T> ObservableSource<T>.wrap(): Observable<T> {
    return Observable.wrap(this)
}