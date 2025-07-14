<<<<<<<< HEAD:presentation/src/main/java/com/example/presentation/viewModels/SearchListViewModel.kt
package com.example.presentation.viewModels
========
package com.madrid.presentation.component.screens.searchscreen

>>>>>>>> develop:presentation/src/main/java/com/madrid/presentation/component/screens/searchscreen/SearchListViewModel.kt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.RecentSearchItem
import com.example.domain.RecentSearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
<<<<<<<< HEAD:presentation/src/main/java/com/example/presentation/viewModels/SearchListViewModel.kt
========
import androidx.lifecycle.ViewModel
import com.madrid.domain.RecentSearchRepository
import com.madrid.domain.RecentSearchItem
import androidx.lifecycle.viewModelScope
>>>>>>>> develop:presentation/src/main/java/com/madrid/presentation/component/screens/searchscreen/SearchListViewModel.kt
import kotlinx.coroutines.launch

class SearchListViewModel(
    private val recentSearchRepository: RecentSearchRepository
) : ViewModel() {
    
    private val _recentSearches = MutableStateFlow<List<RecentSearchItem>>(emptyList())
    val recentSearches: StateFlow<List<RecentSearchItem>> = _recentSearches.asStateFlow()

    init {
        loadRecentSearches()
    }

    private fun loadRecentSearches() {
        viewModelScope.launch {
            _recentSearches.value = recentSearchRepository.getRecentSearches()
        }
    }

    fun addRecentSearch(query: String) {
        viewModelScope.launch {
            val now = System.currentTimeMillis()
            val newItem = RecentSearchItem(
                id = now,
                query = query,
                timestamp = now
            )
            recentSearchRepository.addRecentSearch(newItem)
            loadRecentSearches()
        }
    }

    fun removeRecentSearch(id: Long) {
        viewModelScope.launch {
            recentSearchRepository.removeRecentSearch(id.toString())
            loadRecentSearches()
        }
    }

    fun clearAllRecentSearches() {
        viewModelScope.launch {
            recentSearchRepository.clearAllRecentSearches()
            loadRecentSearches()
        }
    }

    fun clearAll() {
        clearAllRecentSearches()
    }
}