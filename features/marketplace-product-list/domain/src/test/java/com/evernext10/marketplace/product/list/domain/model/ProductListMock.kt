package com.evernext10.marketplace.product.list.domain.model

import com.evernext10.core.domain.model.pokemon.Pokemon
import com.evernext10.core.domain.model.pokemon.response.MarketplacePokemonListResponse

fun productListResponse() = MarketplacePokemonListResponse(
    site_id = "",
    query = "",
    results = listOf(
        Pokemon(
            id = "ASDASD",
            title = "TX",
            price = 1626565,
            currencyId = "COP",
            availableQuantity = 5,
            soldQuantity = 3,
            thumbnail = "",
            condition = "",
            sold = 0,
            pictures = listOf(
                Picture(
                    id = "ASDASD",
                    url = "https://http2.mlstatic.com/D_612440-MCO52044933780_102022-I.jpg"
                )
            )
        )
    )
)
