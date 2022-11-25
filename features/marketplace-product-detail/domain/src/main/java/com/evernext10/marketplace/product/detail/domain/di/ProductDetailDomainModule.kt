package com.evernext10.marketplace.product.detail.domain.di

import com.evernext10.marketplace.product.detail.domain.usecase.GetDetailProductByIdUseCase
import org.koin.dsl.module

val productDetailDomainModule = module {
    single {
        GetDetailProductByIdUseCase(get())
    }
}