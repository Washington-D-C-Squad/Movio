package com.madrid.designSystem.component.textInputField

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.theme.Theme

@Composable
fun BasicTextInputField(
    value: String,
    onValueChange: (String) -> Unit,
    hintText: String,
    startIconPainter: Painter?,
    endIconPainter: Painter?,
    modifier: Modifier = Modifier,
    onClickEndIcon: () -> Unit = { },
    borderBrushColors: List<Color> = listOf(
        Theme.color.brand.onPrimary,
        Theme.color.brand.primary
    ),

    iconColorInFocus: Color = Theme.color.surfaces.onSurface,
    iconColorNotFocus: Color = Theme.color.surfaces.onSurfaceContainer,
    cursorColor: Color = Theme.color.surfaces.onSurface,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    val isFocused by interactionSource.collectIsFocusedAsState()
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = Theme.textStyle.label.smallRegular14.copy(
            color = if (isFocused || value.isNotEmpty())
                Theme.color.surfaces.onSurface
            else
                Theme.color.surfaces.onSurfaceContainer
        ),
        interactionSource = interactionSource,
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                brush = if (isFocused)
                    Brush.horizontalGradient(
                        borderBrushColors
                    )
                else
                    Brush.horizontalGradient(
                        listOf(
                            Theme.color.surfaces.onSurfaceContainer,
                            Theme.color.surfaces.onSurfaceContainer,
                        )
                    ),
                shape = RoundedCornerShape(8.dp)
            )
            .background(Theme.color.surfaces.surfaceContainer, RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 14.dp), // mimic TextField internal padding
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (startIconPainter != null) {
                    Icon(
                        painter = startIconPainter,
                        contentDescription = null,
                        tint = if (isFocused || value.isNotEmpty())
                            iconColorInFocus
                        else {
                            iconColorNotFocus
                        },

                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(20.dp)
                    )
                }

                Box(modifier = Modifier.weight(1f)) {
                    if (value.isEmpty()) {
                        Text(
                            text = hintText,
                            style = Theme.textStyle.label.smallRegular14,
                            color = Theme.color.surfaces.onSurfaceContainer
                        )
                    }
                    innerTextField()
                }

                if (endIconPainter != null && value.isNotEmpty()) {
                    Icon(
                        painter = endIconPainter,
                        contentDescription = null,
                        tint =  if (isFocused || value.isNotEmpty())
                            iconColorInFocus
                        else {
                            iconColorNotFocus
                        },
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(20.dp)
                            .clickable { onClickEndIcon() }
                    )
                }
            }
        },
        cursorBrush = SolidColor(cursorColor),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}