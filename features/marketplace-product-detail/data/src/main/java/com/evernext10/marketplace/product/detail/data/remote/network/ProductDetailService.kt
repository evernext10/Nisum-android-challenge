package com.evernext10.marketplace.product.detail.data.remote.network

import com.evernext10.core.domain.model.product.response.MarketplaceProductDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductDetailService {
    @GET("/items?")
    fun getProductById(
        @Query("ids") ids: String?
    ): Call<List<MarketplaceProductDetailResponse>>
}
