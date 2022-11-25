package com.evernext10.core.domain.model.pokemon.state

import com.evernext10.core.domain.model.pokemon.Pokemon

sealed class StateProductDetail {
    object Loading : StateProductDetail()
    data class Success(val product: Pokemon) : StateProductDetail()
    object Unauthorized : StateProductDetail()
    object Error : StateProductDetail()
}
