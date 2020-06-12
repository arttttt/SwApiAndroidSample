package com.arttttt.swapisampleribs.rib

import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.builder.SimpleBuilder
import org.koin.core.KoinComponent
import org.koin.core.qualifier.Qualifier
import org.koin.ext.getScopeId

abstract class KoinAwareSimpleBuilder<T : Rib>: SimpleBuilder<T>(), KoinComponent {

    @Suppress("LeakingThis")
    protected val scope
        get() = getKoin().getOrCreateScope(getScopeId(), getScopeQualifier())

    abstract fun getScopeQualifier(): Qualifier
}