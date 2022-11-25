package com.evernext10.marketplace.product.detail.data.remote.network

import com.evernext10.core.domain.model.pokemon.response.MarketplaceProductDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductDetailService {
    @GET("/api/v2/pokemon/{id}")
    fun getProductById(
        @Path("id") id: Int?
    ): Call<MarketplaceProductDetailResponse>
}
