package com.arttttt

import com.arkivanov.mvikotlin.core.store.Store

inline fun <reified T : Store<*, *, *>> instanceKey(): String {
	return "${T::class.java.name}_instance"
}

inline fun <reified T : Store<*, *, *>> stateKey(): String {
	return "${T::class.java.name}_state"
}