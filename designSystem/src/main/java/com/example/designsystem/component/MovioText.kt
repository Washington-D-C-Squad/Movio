package com.example.designsystem.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle


@Composable
fun MovioText(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle
) {
    Text(text = text, style = textStyle, modifier = modifier)
}