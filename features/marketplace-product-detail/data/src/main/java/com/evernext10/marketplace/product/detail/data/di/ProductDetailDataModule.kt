package com.evernext10.marketplace.product.detail.data.di

import com.evernext10.core.domain.model.pokemon.response.MarketplaceProductDetailResponse
import com.evernext10.marketplace.product.detail.data.remote.network.ProductDetailService
import com.evernext10.marketplace.product.detail.data.repository.MarketplaceProductDetailRepositoryImpl
import com.evernext10.marketplace.product.detail.domain.repository.MarketplaceProductDetailRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Call
import retrofit2.Retrofit

val productDetailDataModule = module {
    single<MarketplaceProductDetailRepository> {
        MarketplaceProductDetailRepositoryImpl(
            ApiServiceModule(get()),
            androidContext(),
            get()
        )
    }
}

class ApiServiceModule(retrofit: Retrofit) : ProductDetailService {
    private val api by lazy { retrofit.create(ProductDetailService::class.java) }

    override fun getProductById(
        id: Int?
    ): Call<MarketplaceProductDetailResponse> = api.getProductById(id)
}
