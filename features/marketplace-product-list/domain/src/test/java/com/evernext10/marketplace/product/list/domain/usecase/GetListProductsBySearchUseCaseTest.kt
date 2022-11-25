package com.evernext10.marketplace.product.list.domain.usecase

import com.evernext10.core.domain.model.product.response.MarketplaceProductListResponse
import com.evernext10.core.domain.network.Either
import com.evernext10.core.domain.network.Failure
import com.evernext10.marketplace.product.list.domain.model.productListResponse
import com.evernext10.marketplace.product.list.domain.repository.MarketplaceProductListRepository
import com.google.common.truth.Truth
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class GetListProductsBySearchUseCaseTest {

    private lateinit var useCase: GetListProductsBySearchUseCase
    private lateinit var repository: MarketplaceProductListRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        repository = mockk(relaxed = true)
        useCase = GetListProductsBySearchUseCase(repository)
    }

    @Test
    fun `Get products list by word  properly`() = runBlocking {
        coEvery {
            repository.productList("TXL", 30)
        } returns Either.Right(
            productListResponse()
        )
        useCase(
            GetListProductsBySearchUseCase.Params("TXL", 30)
        ) {
            it.fold(
                ::handleFailure,
                ::handleSuccess
            )
        }
    }

    private fun handleFailure(failure: Failure) {}

    private fun handleSuccess(marketplaceProductListResponse: MarketplaceProductListResponse) {
        Truth.assertThat(marketplaceProductListResponse.results).isNotEmpty()
    }

    @After
    fun shutdown() {
        clearAllMocks()
        Dispatchers.resetMain()
    }
}
