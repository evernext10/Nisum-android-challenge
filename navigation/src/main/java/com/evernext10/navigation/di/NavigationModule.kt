package com.evernext10.navigation.di

import com.evernext10.navigation.NavigationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val navigationModule = module {
    viewModel {
        NavigationViewModel()
    }
}
