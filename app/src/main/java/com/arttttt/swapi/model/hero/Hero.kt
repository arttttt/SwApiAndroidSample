package com.arttttt.swapi.model.hero

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Hero(
    @SerializedName("birth_year")
    val birthYear: String,
    val created: String,
    val edited: String,
    @SerializedName("eye_color")
    val eyeColor: String,
//    val films: List<String>,
    val gender: String,
    @SerializedName("hair_color")
    val hairColor: String,
    val height: String,
    @SerializedName("homeworld")
    val homeWorld: String,
    val mass: String,
    val name: String,
    @SerializedName("skin_color")
    val skinColor: String,
//    val species: List<String>,
//    val starships: List<String>,
    @PrimaryKey
    val url: String
//    val vehicles: List<String>
): Parcelable