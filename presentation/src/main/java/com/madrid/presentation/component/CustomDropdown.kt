package com.madrid.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.madrid.designSystem.theme.Theme
import com.madrid.designSystem.component.MovioIcon
import com.madrid.designSystem.component.MovioText
import com.madrid.designSystem.theme.MovioTheme


@Composable
fun <T> CustomDropdown(
    items: List<T>,
    selectedItem: T?,
    labelSelector: (T) -> String,
    onItemSelected: (T) -> Unit,
    modifier: Modifier = Modifier,
    dropdownWidth: Dp = 120.dp
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier.padding(vertical = 2.dp)) {
        Box(
            modifier = Modifier
                .width(dropdownWidth)
                .background(
                    color = Theme.color.surfaces.surfaceContainer,
                    shape = RoundedCornerShape(32.dp)
                )
                .border(
                    width = 1.dp,
                    color = Theme.color.surfaces.onSurfaceAt2,
                    shape = RoundedCornerShape(32.dp)
                )
                .clip(RoundedCornerShape(32.dp))
                .clickable { expanded = true },
            contentAlignment = Alignment.Center
        ) {
            Row(
                Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MovioText(
                    text = selectedItem?.let { labelSelector(it) } ?: "",
                    color = Theme.color.surfaces.onSurfaceContainer,
                    textStyle = Theme.textStyle.label.smallRegular14
                )
                MovioIcon(
                    painter = painterResource(com.madrid.designSystem.R.drawable.icon_arrow_down),
                    contentDescription = "icon arrow down icon",
                    tint = Theme.color.surfaces.onSurfaceVariant
                )
            }
        }
        AnimatedVisibility(
            visible = expanded,
            enter = slideInVertically() + fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .width(dropdownWidth)
                .zIndex(1f)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = Theme.color.surfaces.surface,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = Theme.color.surfaces.onSurfaceAt2,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp)
            ) {
                Column {
                    items.forEach { item ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onItemSelected(item)
                                    expanded = false
                                }
                                .padding(8.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            MovioText(
                                text = labelSelector(item),
                                color = Theme.color.surfaces.onSurfaceContainer,
                                textStyle = Theme.textStyle.label.smallRegular14
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CustomDropdownPreview() {
    MovioTheme {
        var selectedItem by remember { mutableStateOf("Option 1") }
        CustomDropdown(
            items = listOf("Option 1", "Option 2", "Option 3"),
            selectedItem = selectedItem,
            labelSelector = { it },
            onItemSelected = { selectedItem = it }
        )
    }
}