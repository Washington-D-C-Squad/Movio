package com.madrid.presentation.composables.videoLibrary

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.dp


@Composable
fun GridWithFullBorders(modifier: Modifier = Modifier, highlightedCells: Set<Pair<Int, Int>>) {
    val isDark = isSystemInDarkTheme()

    Canvas(modifier = modifier) {

        val cols = 9
        val rows = 7

        val squareWidth = size.width / cols
        val squareHeight = size.height / rows

        for (row in 0 until rows) {
            for (col in 0 until cols) {
                val x = col * squareWidth
                val y = row * squareHeight

                val isHighlighted = highlightedCells.contains(Pair(row, col))
                val rectColor = if (isDark) {
                    if (isHighlighted) Color(0x40FFFFFF) else Color.Transparent
                } else Color.Transparent

                drawRect(
                    color = rectColor,
                    topLeft = Offset(x, y),
                    size = Size(squareWidth, squareHeight),
                    style = Fill
                )
            }
        }

        val lineColor = if (isDark) Color.White else Color.Black
        val strokeWidth = 0.17.dp.toPx()

        for (col in 0..cols) {
            val x = col * squareWidth
            drawLine(
                color = lineColor,
                start = Offset(x, 0f),
                end = Offset(x, size.height),
                strokeWidth = strokeWidth
            )
        }
        for (row in 0..rows) {
            val y = row * squareHeight
            drawLine(
                color = lineColor,
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = strokeWidth
            )
        }
    }
}