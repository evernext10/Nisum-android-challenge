package com.evernext10.marketplace.product.list.data.remote.network

import com.evernext10.core.domain.model.product.response.MarketplaceProductListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductListService {
    @GET("/sites/MCO/search?")
    fun getProductsBySearch(
        @Query("q") q: String?,
        @Query("limit") limit: Int?
    ): Call<MarketplaceProductListResponse>
}
