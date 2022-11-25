package com.evernext10.core.domain.model.pokemon.response

import com.evernext10.core.domain.model.pokemon.Pokemon
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarketplacePokemonListResponse(
    @Json(name = "count")
    val count: Int = 0,
    @Json(name = "next")
    val next: String = "",
    @Json(name = "previous")
    val previous: String = "",
    @Json(name = "results")
    val results: List<Pokemon> = emptyList()
)
