package com.madrid.presentation

import androidx.lifecycle.ViewModel
import com.madrid.domain.interfaces.SearchRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchViewModel : ViewModel(), KoinComponent {
    private val searchRepository: SearchRepository by inject()
}