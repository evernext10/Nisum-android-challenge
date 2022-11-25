package com.evernext10.marketplace.product.list.domain.model

import com.evernext10.core.domain.model.product.Picture
import com.evernext10.core.domain.model.product.Product
import com.evernext10.core.domain.model.product.response.MarketplaceProductListResponse

fun productListResponse() = MarketplaceProductListResponse(
    site_id = "",
    query = "",
    results = listOf(
        Product(
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
