package com.evernext10.marketplace.product.detail.domain.repository

import com.evernext10.core.domain.model.pokemon.response.MarketplaceProductDetailResponse
import com.evernext10.core.domain.network.Either
import com.evernext10.core.domain.network.Failure

interface MarketplaceProductDetailRepository {
    suspend fun productDetail(
        id: Int?
    ): Either<Failure, MarketplaceProductDetailResponse>
}
