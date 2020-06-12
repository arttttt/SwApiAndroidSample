package com.arttttt.swapisampleribs.di

import com.arttttt.swapisampleribs.data.network.api.SwApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val apiModule = module {
    single<SwApi> { get<Retrofit>().create() }
}