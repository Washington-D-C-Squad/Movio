package com.example.presentation.component.filteredImage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.detectImageContent.GetImageBitmap
import com.example.detectImageContent.SensitiveContentDetection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FilteredImageViewModel(
    private val getImageBitmap: GetImageBitmap,
    private val contentDetector: SensitiveContentDetection
) : ViewModel() {

    private val _uiState = MutableStateFlow(FilteredImageUiState())
    val state = _uiState.asStateFlow()

    fun loadAndAnalyze(imageUrl: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val bitmapImage = getImageBitmap.getImageBitmapFromUrl(imageUrl)
            val isNSFW = contentDetector.sensitiveContentDetection(bitmapImage)
            _uiState.update {
                it.copy(
                    bitmap = bitmapImage,
                    isNSFW = isNSFW,
                )
            }
        }
    }
}
