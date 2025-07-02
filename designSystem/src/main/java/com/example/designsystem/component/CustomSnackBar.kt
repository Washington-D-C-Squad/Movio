package com.example.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.designsystem.AppTheme
import kotlinx.coroutines.delay
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


fun ShowSnackbar(
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
                            Text(
                                label,
                                style = AppTheme.textStyle.label.medium14,
                                color = AppTheme.colors.brandColors.primary
                            )
                        }
                    }
                }
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = imagePainter, contentDescription = "", tint = Color.Unspecified)
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