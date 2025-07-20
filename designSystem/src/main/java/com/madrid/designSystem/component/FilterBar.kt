package com.madrid.designSystem.component

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
import com.madrid.designSystem.theme.Theme

@Composable
fun FilterBar(
    items: List<String>,
    selectedItem: String,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    scrollable: Boolean = true,
) {
    if (scrollable) {
        LazyRow(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
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
                .padding(horizontal = 16.dp)
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
        style = Theme.textStyle.body.mediumMedium14
    )
    val textWidthDp = with(density) { textLayoutResult.size.width.toDp() }
    val targetColor = if (isSelected) {
        Theme.color.surfaces.surface
    } else {
        Theme.color.surfaces.onSurfaceVariant
    }

    val textColor by animateColorAsState(
        targetValue = targetColor,
        label = "FilterChipTextColor"
    )
    Box(
        modifier = modifier

            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column {
            MovioText(
                text,
                color = textColor,
                textStyle = Theme.textStyle.body.mediumMedium14
            )
            AnimatedVisibility(
                visible = isSelected
            ) {
                Box(
                    modifier = Modifier
                            .padding(top = 4.dp)
                        .height(1.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .width(textWidthDp)
                        .background(brush = underlineGlowBrush())
                )
            }
        }
    }
}
