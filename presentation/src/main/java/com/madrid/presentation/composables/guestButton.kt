package com.madrid.presentation.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.component.MovioButton
import com.madrid.designsystem.component.MovioText
import com.madrid.presentation.R

@Composable
fun GuestButton(
    modifier: Modifier = Modifier,
    color: Color = Color.Transparent,
    text: String = stringResource(R.string.continue_as_a_guest),
    onClick: () -> Unit,
) {


    MovioButton(
        modifier = modifier
            .padding(horizontal = AppTheme.spacing.medium)

            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = AppTheme.colors.surfaceColor.onSurface_3,
                shape = RoundedCornerShape(AppTheme.radius.xxLarge)
            ),
        color = color,
        onClick = onClick
    ) {
        MovioText(
            modifier = Modifier.padding(vertical = AppTheme.spacing.medium),
            text = text,
            color = AppTheme.colors.surfaceColor.onSurface,
            textStyle = AppTheme.textStyle.label.medium14,
        )
    }
}