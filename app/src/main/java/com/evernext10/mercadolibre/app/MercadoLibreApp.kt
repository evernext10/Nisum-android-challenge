package com.evernext10.mercadolibre.app

import android.app.Application
import com.evernext10.core.data.di.coreModule
import com.evernext10.marketplace.product.detail.data.di.productDetailDataModule
import com.evernext10.marketplace.product.detail.domain.di.productDetailDomainModule
import com.evernext10.marketplace.product.detail.presentation.di.productDetailPresentationModule
import com.evernext10.marketplace.product.list.data.di.productListDataModule
import com.evernext10.marketplace.product.list.domain.di.productListDomainModule
import com.evernext10.marketplace.product.list.presentation.di.productListPresentationModule
import com.evernext10.navigation.di.navigationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MercadoLibreApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MercadoLibreApp)
            modules(
                listOf(
                    coreModule,
                    productListDataModule,
                    productListDomainModule,
                    productListPresentationModule,
                    navigationModule,
                    productDetailDataModule,
                    productDetailDomainModule,
                    productDetailPresentationModule
                )
            )
        }
    }
}
