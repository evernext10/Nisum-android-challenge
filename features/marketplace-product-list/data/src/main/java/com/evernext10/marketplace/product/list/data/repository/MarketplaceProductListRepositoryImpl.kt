package com.evernext10.marketplace.product.list.data.repository

import android.content.Context
import com.evernext10.core.app.isNetworkAvailable
import com.evernext10.core.domain.model.product.response.MarketplaceProductListResponse
import com.evernext10.core.domain.network.Either
import com.evernext10.core.domain.network.Failure
import com.evernext10.core.domain.network.request
import com.evernext10.marketplace.product.list.data.di.ApiServiceModule
import com.evernext10.marketplace.product.list.domain.repository.MarketplaceProductListRepository

class MarketplaceProductListRepositoryImpl(
    private val api: ApiServiceModule,
    private val context: Context
) : MarketplaceProductListRepository {
    override suspend fun productList(
        search: String?,
        limit: Int
    ): Either<Failure, MarketplaceProductListResponse> {
        return when (context.isNetworkAvailable()) {
            true -> {
                request(
                    api.getProductsBySearch(search, limit),
                    { it },
                    MarketplaceProductListResponse()
                )
            }
            false -> Either.Left(Failure.NetworkConnection)
        }
    }
}
