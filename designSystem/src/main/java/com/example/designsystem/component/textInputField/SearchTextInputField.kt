package com.example.designsystem.component.textInputField

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.designsystem.AppTheme
import com.example.designsystem.component.MovioIcon
import com.example.designsystem.component.MovioText
import com.example.designsystem.R

@Composable
fun SearchTextInputField(
    value: String,
    onValueChange: (String) -> Unit,
    hintText: String = "Search",
    modifier: Modifier = Modifier,
    onClear: () -> Unit = {},
    startIconPainter: Painter = painterResource(id = R.drawable.search_normal),
    endIconPainter: Painter = painterResource(id = R.drawable.outline_add),
    backgroundColor: Color = AppTheme.colors.surfaceColor.surfaceContainer,
    iconColor: Color = AppTheme.colors.surfaceColor.onSurfaceVariant,
    hintColor: Color = AppTheme.colors.surfaceColor.onSurfaceContainer,
    textColor: Color = AppTheme.colors.surfaceColor.onSurface,
    imeAction: ImeAction = ImeAction.Default,
    onImeAction: () -> Unit = {}
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = AppTheme.textStyle.label.smallRegular14.copy(color = textColor),
        cursorBrush = SolidColor(iconColor),
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth()
            .background(color = backgroundColor, shape = RoundedCornerShape(AppTheme.radius.medium)),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = imeAction),
        keyboardActions = KeyboardActions(
            onDone = { onImeAction() },
            onSearch = { onImeAction() }
        ),
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = AppTheme.spacing.medium)
            ) {
                MovioIcon(
                    painter = startIconPainter,
                    contentDescription = "Search Icon",
                    tint = iconColor,
                    modifier = Modifier.size(20.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))
                Box(modifier = Modifier.weight(1f)) {
                    if (value.isEmpty()) {
                        MovioText(
                            text = hintText,
                            textStyle = AppTheme.textStyle.label.smallRegular14,
                            color = hintColor
                        )
                    }
                    innerTextField()
                }
                Spacer(modifier = Modifier.width(10.dp))
                MovioIcon(
                    painter = endIconPainter,
                    contentDescription = "Clear Icon",
                    tint = iconColor,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable(enabled = value.isNotEmpty()) { onClear() }
                )
            }
        }
    )
}
