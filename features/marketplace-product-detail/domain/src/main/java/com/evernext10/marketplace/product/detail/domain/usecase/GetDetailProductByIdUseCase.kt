package com.evernext10.marketplace.product.detail.domain.usecase

import com.evernext10.core.domain.model.pokemon.response.MarketplaceProductDetailResponse
import com.evernext10.core.domain.network.Either
import com.evernext10.core.domain.network.Failure
import com.evernext10.core.domain.network.UseCase
import com.evernext10.marketplace.product.detail.domain.repository.MarketplaceProductDetailRepository

class GetDetailProductByIdUseCase constructor(
    private val mercadoLibreRepository: MarketplaceProductDetailRepository
) : UseCase<MarketplaceProductDetailResponse, GetDetailProductByIdUseCase.Params>() {
    override suspend fun run(params: Params): Either<Failure, MarketplaceProductDetailResponse> {
        return mercadoLibreRepository.productDetail(params.id)
    }

    data class Params(val id: Int?)
}
