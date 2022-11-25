package com.evernext10.marketplace.product.list.domain.usecase

import com.evernext10.core.domain.model.pokemon.response.MarketplacePokemonListResponse
import com.evernext10.core.domain.network.Either
import com.evernext10.core.domain.network.Failure
import com.evernext10.core.domain.network.UseCase
import com.evernext10.marketplace.product.list.domain.repository.MarketplaceProductListRepository

class GetListProductsBySearchUseCase constructor(
    private val mercadoLibreRepository: MarketplaceProductListRepository
) : UseCase<MarketplacePokemonListResponse, GetListProductsBySearchUseCase.Params>() {
    override suspend fun run(params: Params): Either<Failure, MarketplacePokemonListResponse> {
        return mercadoLibreRepository.productList(params.offset, params.limit)
    }

    data class Params(val offset: Int, val limit: Int)
}
