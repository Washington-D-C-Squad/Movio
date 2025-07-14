package com.example.designsystem.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.AnnotatedString

@Composable
fun MovioText(
    text: String = "",
    color: Color,
    textStyle: TextStyle,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    annotatedString: AnnotatedString? = null
) {
    if (annotatedString != null) {
        Text(
            text = annotatedString,
            style = textStyle.copy(color = color),
            modifier = modifier,
            maxLines = maxLines,
            overflow = overflow
        )
    } else {
        Text(
            text = text,
            style = textStyle.copy(color = color),
            modifier = modifier,
            maxLines = maxLines,
            overflow = overflow
        )
    }
}