package com.evernext10.marketplace.product.list.domain.di

import com.evernext10.marketplace.product.list.domain.usecase.GetListProductsBySearchUseCase
import org.koin.dsl.module

val productListDomainModule = module {

    single {
        GetListProductsBySearchUseCase(get())
    }
}