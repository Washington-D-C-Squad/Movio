package com.madrid.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.theme.Theme
import com.madrid.designSystem.component.MovioButton
import com.madrid.designSystem.component.MovioText
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
            .padding(horizontal = 16.dp)

            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Theme.color.surfaces.onSurfaceAt3,
                shape = RoundedCornerShape(32.dp)
            ),
        color = color,
        onClick = onClick
    ) {
        MovioText(
            modifier = Modifier.padding(vertical = 16.dp),
            text = text,
            color = Theme.color.surfaces.onSurface,
            textStyle = Theme.textStyle.label.mediumMedium14,
        )
    }
}