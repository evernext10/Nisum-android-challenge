package com.evernext10.marketplace.product.list.presentation.di

import com.evernext10.marketplace.product.list.presentation.ui.ProductListScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productListPresentationModule = module {
    viewModel {
        ProductListScreenViewModel(
            get(),
            get()
        )
    }
}
