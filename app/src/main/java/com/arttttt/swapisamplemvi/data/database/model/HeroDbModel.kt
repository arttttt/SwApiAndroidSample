package com.arttttt.swapisamplemvi.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "heroes_table"
)
class HeroDbModel(
    @PrimaryKey
    val name: String
)