package com.evernext10.marketplace.product.list.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evernext10.core.domain.model.pokemon.response.MarketplacePokemonListResponse
import com.evernext10.core.domain.model.pokemon.state.StatePokemonList
import com.evernext10.core.domain.network.Failure
import com.evernext10.marketplace.product.list.domain.usecase.GetListProductsBySearchUseCase

class ProductListScreenViewModel constructor(
    private val useCase: GetListProductsBySearchUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _productListState: MutableLiveData<StatePokemonList> = MutableLiveData()
    val productListState: LiveData<StatePokemonList> = _productListState

    private val _randomWord: MutableLiveData<String> = MutableLiveData()
    val randomWord: LiveData<String> = _randomWord

    private var _queryWord: MutableLiveData<String> = MutableLiveData()
    val queryWord: LiveData<String> = _queryWord

    fun getDataFromStateHandled() {
        if (savedStateHandle.contains(KEY_STATE)) {
            _productListState.postValue(StatePokemonList.Success(savedStateHandle[KEY_STATE]!!))
        }
    }

    fun getRandomWordToShowInitialData() {
        _randomWord.postValue(RANDOM_LIST_ITEMS.random())
    }

    init {
        getMarketplacePockemonList(150, 150)
    }

    fun getMarketplacePockemonList(offset: Int = 150, limit: Int = 150) {
        useCase(
            GetListProductsBySearchUseCase.Params(offset, limit),
            viewModelScope
        ) {
            it.fold(
                ::handleFailure,
                ::handleSuccess
            )
        }
    }

    fun postQueryWord(query: String) = _queryWord.postValue(query)

    private fun handleSuccess(response: MarketplacePokemonListResponse) {
        savedStateHandle[KEY_STATE] = response.results
        _productListState.postValue(StatePokemonList.Success(response.results))
    }

    private fun handleFailure(failure: Failure) {
        _productListState.postValue(StatePokemonList.Error)
    }

    companion object {
        val RANDOM_LIST_ITEMS = listOf("TXL", "PS5", "Teclado", "PC", "iphone14")
        const val KEY_STATE = "saved_product_list"
    }
}
