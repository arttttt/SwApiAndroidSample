package com.arttttt.swapisampleribs.data.network.model

abstract class SwApiResponse<T> {
    abstract val count: Int
    abstract val next: String?
    abstract val previous: String?
    abstract val results: List<T>
}