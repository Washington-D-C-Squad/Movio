//package com.example.presentation.di

import com.example.presentation.component.viewmodel.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { SearchViewModel(get()) }
}