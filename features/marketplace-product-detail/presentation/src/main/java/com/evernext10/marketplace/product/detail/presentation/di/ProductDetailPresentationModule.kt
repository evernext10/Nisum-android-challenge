package com.evernext10.marketplace.product.detail.presentation.di

import com.evernext10.marketplace.product.detail.presentation.ui.ProductDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productDetailPresentationModule = module {
    viewModel {
        ProductDetailViewModel(
            get(),
            get()
        )
    }
}
