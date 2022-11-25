package com.evernext10.marketplace.product.list.domain.repository

import com.evernext10.core.domain.model.product.response.MarketplaceProductListResponse
import com.evernext10.core.domain.network.Either
import com.evernext10.core.domain.network.Failure

interface MarketplaceProductListRepository {
    suspend fun productList(
        search: String?,
        limit: Int = 50
    ): Either<Failure, MarketplaceProductListResponse>
}
