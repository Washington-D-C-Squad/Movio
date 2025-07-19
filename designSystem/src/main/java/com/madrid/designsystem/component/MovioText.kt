package com.madrid.designsystem.component

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun MovioText(
    text: String,
    color: Color,
    textStyle: TextStyle,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Ellipsis
) {
    BasicText(
        text = text, style = textStyle.copy(color = color),
        modifier = modifier, maxLines = maxLines, overflow = overflow
    )
}

@Composable
fun MovioTextHighlight(
    text: AnnotatedString,
    color: Color,
    textStyle: TextStyle,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Ellipsis
) {
    BasicText(
        text = text, style = textStyle.copy(color = color),
        modifier = modifier, maxLines = maxLines, overflow = overflow
    )
}