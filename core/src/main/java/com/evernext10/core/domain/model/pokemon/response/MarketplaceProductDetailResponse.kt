package com.evernext10.core.domain.model.pokemon.response

import com.evernext10.core.domain.model.pokemon.Pokemon
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarketplaceProductDetailResponse(
    @Json(name = "code")
    val code: Int = 0,
    @Json(name = "body")
    val body: Pokemon = Pokemon()
)
