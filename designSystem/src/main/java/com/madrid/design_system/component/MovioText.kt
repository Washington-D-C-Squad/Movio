package com.madrid.design_system.component

import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun MovioText(
    text: String,
    color: Color,
    textStyle: TextStyle,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,

    textAlign: TextAlign? = null ,
    overflow: TextOverflow = TextOverflow.Ellipsis
) {
    Text(
        text = text,
        textAlign = textAlign,
        style = textStyle.copy(color = color),
        modifier = modifier,
        maxLines = maxLines,
        overflow = overflow

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