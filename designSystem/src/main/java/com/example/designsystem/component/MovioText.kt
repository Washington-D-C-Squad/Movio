package com.example.designsystem.component

import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow


@Composable
fun MovioText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color,
    textStyle: TextStyle,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Ellipsis
) {
     Text(
         text = text, style = textStyle.copy(color = color),
         modifier = modifier,maxLines=maxLines,overflow=overflow
     )
}