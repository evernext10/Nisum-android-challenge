package com.evernext10.marketplace.product.list.data.remote.network

import com.evernext10.core.domain.model.pokemon.response.MarketplacePokemonListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductListService {
    @GET("pokemon/?")
    fun getProductsBySearch(
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?
    ): Call<MarketplacePokemonListResponse>
}
