package com.evernext10.marketplace.product.list.domain.usecase

import com.evernext10.core.domain.model.product.response.MarketplaceProductListResponse
import com.evernext10.core.domain.network.Either
import com.evernext10.core.domain.network.Failure
import com.evernext10.core.domain.network.UseCase
import com.evernext10.marketplace.product.list.domain.repository.MarketplaceProductListRepository

class GetListProductsBySearchUseCase constructor(
    private val mercadoLibreRepository: MarketplaceProductListRepository
) : UseCase<MarketplaceProductListResponse, GetListProductsBySearchUseCase.Params>() {
    override suspend fun run(params: Params): Either<Failure, MarketplaceProductListResponse> {
        return mercadoLibreRepository.productList(params.search, params.limit)
    }

    data class Params(val search: String?, val limit: Int)
}
