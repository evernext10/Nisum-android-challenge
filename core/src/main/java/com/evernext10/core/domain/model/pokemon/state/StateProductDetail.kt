package com.evernext10.core.domain.model.pokemon.state

import com.evernext10.core.domain.model.pokemon.Pokemon
import com.evernext10.core.domain.model.pokemon.response.MarketplaceProductDetailResponse

sealed class StateProductDetail {
    object Loading : StateProductDetail()
    data class Success(val response: MarketplaceProductDetailResponse) : StateProductDetail()
    object Unauthorized : StateProductDetail()
    object Error : StateProductDetail()
}
