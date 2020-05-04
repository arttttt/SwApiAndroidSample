package com.arttttt.swapisamplemvi.data.database.mapping

import com.arttttt.swapisamplemvi.data.database.model.HeroDbModel
import com.arttttt.swapisamplemvi.domain.entity.Hero

fun HeroDbModel.toEntity(): Hero {
    return Hero(
        name = this.name,
        birthYear = this.birthYear
    )
}