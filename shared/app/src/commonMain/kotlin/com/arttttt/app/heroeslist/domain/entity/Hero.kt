package com.arttttt.app.heroeslist.domain.entity

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
data class Hero(
    val id: Int,
    val name: String
) : Parcelable
