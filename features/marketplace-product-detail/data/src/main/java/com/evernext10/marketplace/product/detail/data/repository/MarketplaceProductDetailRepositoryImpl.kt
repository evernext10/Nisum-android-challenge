package com.evernext10.marketplace.product.detail.data.repository

import android.content.Context
import com.evernext10.core.app.isNetworkAvailable
import com.evernext10.core.data.local.dao.PokemonDao
import com.evernext10.core.domain.model.pokemon.response.MarketplaceProductDetailResponse
import com.evernext10.core.domain.network.Either
import com.evernext10.core.domain.network.Failure
import com.evernext10.core.domain.network.request
import com.evernext10.marketplace.product.detail.data.di.ApiServiceModule
import com.evernext10.marketplace.product.detail.domain.repository.MarketplaceProductDetailRepository

class MarketplaceProductDetailRepositoryImpl(
    private val api: ApiServiceModule,
    private val context: Context,
    private val pokemonDao: PokemonDao
) : MarketplaceProductDetailRepository {

    override suspend fun productDetail(
        id: Int?
    ): Either<Failure, MarketplaceProductDetailResponse> {
        return when (context.isNetworkAvailable()) {
            true -> {
                request(
                    api.getProductById(id),
                    { it },
                    MarketplaceProductDetailResponse(),
                    pokemonDao
                )
            }
            false -> Either.Left(Failure.NetworkConnection)
        }
    }
}
