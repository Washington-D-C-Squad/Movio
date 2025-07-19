package com.madrid.designsystem.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import com.madrid.designsystem.AppTheme
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovioBottomSheet(
    show: Boolean,
    onDismiss: () -> Unit,
    containerColor: Color = AppTheme.colors.surfaceColor.surface,
    content: @Composable () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    if (show) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState,
            containerColor = containerColor,
        ) {
            content()
        }
    }
}
