package com.arttttt.swapi

import com.arttttt.moduleinjector.BaseFeatureApi
import com.arttttt.swapi.data.api.SwApi

interface SwApiFeatureApi : BaseFeatureApi {

    val swApi: SwApi
}
