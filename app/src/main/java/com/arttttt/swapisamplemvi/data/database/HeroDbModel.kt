package com.arttttt.swapisamplemvi.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "heroes_table"
)
class HeroDbModel(
    @PrimaryKey
    val name: String
)