package com.example.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.example.presentation.SearchViewModel

val presentationModule = module {
    viewModel { SearchViewModel() }
} 