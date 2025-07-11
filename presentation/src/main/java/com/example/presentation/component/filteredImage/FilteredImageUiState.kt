package com.example.presentation.component.filteredImage

import android.graphics.Bitmap

data class FilteredImageUiState(
    val bitmap: Bitmap? = null,
    val isNSFW: Boolean = false,
    val error: String? = null
)