package com.evernext10.marketplace.product.list.domain.repository

import com.evernext10.core.domain.model.pokemon.response.MarketplacePokemonListResponse
import com.evernext10.core.domain.network.Either
import com.evernext10.core.domain.network.Failure

interface MarketplaceProductListRepository {
    suspend fun productList(
        offset: Int = 150,
        limit: Int = 150
    ): Either<Failure, MarketplacePokemonListResponse>
}
