package com.evernext10.marketplace.product.detail.presentation.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evernext10.core.domain.model.pokemon.response.MarketplaceProductDetailResponse
import com.evernext10.core.domain.model.pokemon.state.StateProductDetail
import com.evernext10.core.domain.network.Failure
import com.evernext10.marketplace.product.detail.domain.usecase.GetDetailProductByIdUseCase

class ProductDetailViewModel(
    private val useCase: GetDetailProductByIdUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _productDetailState: MutableLiveData<StateProductDetail> = MutableLiveData()
    val productDetailState: LiveData<StateProductDetail> = _productDetailState

    fun getDataFromStateHandled(pockemonId: Int) {
        if (savedStateHandle.contains(KEY_STATE)) {
            _productDetailState.postValue(StateProductDetail.Success(savedStateHandle[KEY_STATE]!!))
        } else {
            getMarketplaceProductDetail(pockemonId)
        }
    }

    private fun getMarketplaceProductDetail(pockemonId: Int) {
        useCase(
            GetDetailProductByIdUseCase.Params(pockemonId),
            viewModelScope
        ) {
            it.fold(
                ::handleFailure,
                ::handleSuccess
            )
        }
    }

    private fun handleSuccess(response: MarketplaceProductDetailResponse) {
        savedStateHandle[KEY_STATE] = response
        _productDetailState.postValue(StateProductDetail.Success(response))
    }

    private fun handleFailure(failure: Failure) {
        Log.i("Error", failure.toString())
        _productDetailState.postValue(StateProductDetail.Error)
    }

    companion object {
        const val KEY_STATE = "saved_product_detail"
    }
}
