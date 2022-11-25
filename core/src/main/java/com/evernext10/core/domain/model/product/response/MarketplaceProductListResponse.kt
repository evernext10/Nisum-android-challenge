package com.evernext10.core.domain.model.product.response

import com.evernext10.core.domain.model.product.Product
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarketplaceProductListResponse(
    @Json(name = "site_id")
    val site_id: String = "",
    @Json(name = "query")
    val query: String = "",
    @Json(name = "paging")
    val paging: Paging = Paging(),
    @Json(name = "results")
    val results: List<Product> = emptyList()
)
