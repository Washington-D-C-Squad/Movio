package com.example.presentation

import androidx.lifecycle.ViewModel
import com.example.domain.repository.SearchRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchViewModel : ViewModel(), KoinComponent {
    private val searchRepository: SearchRepository by inject()
}