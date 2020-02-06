package com.arttttt.swapisamplemvi.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "heroes_table"
)
class HeroDbModel(
    @PrimaryKey
    val name: String,
    val birthYear: String,
    val created: String,
    val edited: String,
    val eyeColor: String,
    val gender: String,
    val hairColor: String,
    val height: String,
    val homeWorld: String,
    val mass: String,
    val skinColor: String,
    val url: String
)