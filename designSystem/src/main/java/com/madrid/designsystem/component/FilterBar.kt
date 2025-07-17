package com.example.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.component.MovioText

@Composable
fun FilterbBar(
    items: List<String>,
    selectedItem: String,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    scrollable: Boolean = true,
) {
    if (scrollable) {
        LazyRow(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.small),
            contentPadding = PaddingValues(horizontal = AppTheme.spacing.medium),
            ) {
            items(items) { item ->
                FilterChip(
                    text = item,
                    isSelected = item == selectedItem,
                    onClick = { onItemClick(item) })
            }
        }
    } else {
        Row(
            modifier = modifier
                .padding(horizontal = AppTheme.spacing.medium)
        ) {
            items.forEach { item ->
                FilterChip(
                    text = item,
                    isSelected = item == selectedItem,
                    onClick = { onItemClick(item) },
                )
            }
        }
    }
}
@Composable
fun FilterChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val textMeasurer = rememberTextMeasurer()
    val density = LocalDensity.current
    val textLayoutResult = textMeasurer.measure(
        text = AnnotatedString(text),
        style = AppTheme.textStyle.body.medium14
    )
    val textWidthDp = with(density) { textLayoutResult.size.width.toDp() }
    val targetColor = if (isSelected) {
        AppTheme.colors.surfaceColor.surface
    } else {
        AppTheme.colors.surfaceColor.onSurfaceVariant
    }

    val textColor by animateColorAsState(
        targetValue = targetColor,
        label = "FilterChipTextColor"
    )
    Box(
        modifier = modifier

            .clickable(onClick = onClick)
            .padding(horizontal = AppTheme.spacing.medium, vertical = AppTheme.spacing.small)
    ) {
        Column {
            MovioText(
                text,
                color = textColor,
                textStyle = AppTheme.textStyle.body.medium14
            )
            AnimatedVisibility(
                visible = isSelected
            ) {
                Box(
                    modifier = Modifier
                            .padding(top = AppTheme.spacing.extraSmall)
                        .height(1.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .width(textWidthDp)
                        .background(brush = underlineGlowBrush())
                )
            }
        }
    }
}@Composable
fun underlineGlowBrush(): Brush {
    val glowColor = AppTheme.colors.brandColors.onPrimaryContainer

    return Brush.horizontalGradient(
        colors = listOf(
            glowColor.copy(alpha = 0f),
            glowColor.copy(alpha = 0.5f),
            glowColor.copy(alpha = 0.1f)
        )
    )
}
