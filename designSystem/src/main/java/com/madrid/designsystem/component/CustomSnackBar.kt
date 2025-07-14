package com.madrid.designsystem.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.madrid.designsystem.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


fun showSnackbar(
    scope: CoroutineScope,
    hostState: SnackbarHostState,
    message: String,
    actionLabel: String? = null,
) {
    scope.launch {
        hostState.showSnackbar(
            message = message,
            actionLabel = actionLabel,
            duration = SnackbarDuration.Short
        )
    }
}

@Composable
fun CustomSnackbarHost(
    snackbarHostState: SnackbarHostState,
    imagePainter: Painter,
    onUndoClick: (() -> Unit)? = null
) {
    SnackbarHost(
        hostState = snackbarHostState,
        snackbar = { snackbarData ->
            Snackbar(
                modifier = Modifier.padding(vertical = 14.dp, horizontal = 16.dp),
                containerColor = AppTheme.colors.surfaceColor.surfaceContainer,
                contentColor = AppTheme.colors.surfaceColor.onSurface,
                shape = RoundedCornerShape(AppTheme.radius.small),
                action = {
                    snackbarData.visuals.actionLabel?.let { label ->
                        TextButton(
                            onClick = {
                                snackbarData.dismiss()
                                onUndoClick?.invoke()
                            }
                        ) {
                            MovioText(
                                text = label,
                                textStyle = AppTheme.textStyle.label.medium14,
                                color = AppTheme.colors.brandColors.primary
                            )
                        }
                    }
                }
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    MovioIcon(
                        painter = imagePainter,
                        contentDescription = "",
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.padding(horizontal = AppTheme.spacing.small))
                    MovioText(
                        text = snackbarData.visuals.message,
                        color = LocalContentColor.current,
                        textStyle = AppTheme.textStyle.label.smallRegular14
                    )
                }

            }
        }
    )
}