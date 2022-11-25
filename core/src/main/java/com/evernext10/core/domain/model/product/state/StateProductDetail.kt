package com.evernext10.core.domain.model.product.state

import com.evernext10.core.domain.model.product.Product

sealed class StateProductDetail {
    object Loading : StateProductDetail()
    data class Success(val product: Product) : StateProductDetail()
    object Unauthorized : StateProductDetail()
    object Error : StateProductDetail()
}
