package com.arttttt.root

import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.coroutines.cancellation.CancellationException

inline fun <R> Result<R>.finally(
    finally: () -> Unit,
): Result<R> {
    return try {
        this
    } finally {
        finally.invoke()
    }
}

fun <R> Result<R>.exceptCancellationException(): Result<R> {
    return this.except<CancellationException, R>()
}

inline fun <reified T, R> Result<R>.except(): Result<R> {
    return onFailure { if (it is T) throw it }
}

fun <T : Any> Value<T>.asStateFlow(): StateFlow<T> = ValueStateFlow(this)

private class ValueStateFlow<out T : Any>(
    private val v: Value<T>,
) : StateFlow<T> {
    override val value: T get() = v.value
    override val replayCache: List<T> get() = listOf(v.value)

    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        val flow = MutableStateFlow(v.value)
        val observer: (T) -> Unit = { flow.value = it }
        v.subscribe(observer)

        try {
            flow.collect(collector)
        } finally {
            v.unsubscribe(observer)
        }
    }
}
