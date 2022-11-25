package com.evernext10.core.domain.model.pokemon.response

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class MarketplaceProductDetailResponse(
    val sprites: Sprites? = null,
    val stats: List<Stats> = emptyList(),
    val height: Int? = null,
    val weight: Int? = null
) : Parcelable

@Parcelize
data class Sprites(
    val back_default: String,
    val back_shiny: String,
    val front_default: String,
    val front_shiny: String
) : Parcelable

@Parcelize
data class Stats(
    val base_stat: Int,
    val effort: Int,
    val stat: Stat
) : Parcelable

@Parcelize
data class Stat(
    val name: String,
    val url: String
) : Parcelable
