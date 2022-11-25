package com.evernext10.core.domain.model.product.state

import com.evernext10.core.domain.model.product.Product

sealed class StateProductList {
    object Loading : StateProductList()
    data class Success(val products: List<Product>) : StateProductList()
    object Unauthorized : StateProductList()
    object Error : StateProductList()
}
