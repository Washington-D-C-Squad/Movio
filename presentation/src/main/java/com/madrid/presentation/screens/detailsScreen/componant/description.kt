package com.madrid.presentation.screens.detailsScreen.componant

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.component.MovioText
import com.madrid.designSystem.theme.Theme


@Composable
fun ExpandableDescription(
    description: String,
    modifier: Modifier = Modifier,
    maxLines: Int = 4
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        MovioText(
            text = description,
            color = Theme.color.surfaces.onSurface,
            textStyle = Theme.textStyle.body.mediumMedium14,
            maxLines = if (isExpanded) Int.MAX_VALUE else maxLines,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        if (shouldShowReadMore(description, maxLines)) {
            TextButton(
                onClick = { isExpanded = !isExpanded },
                modifier = Modifier.padding(start = 16.dp)
            ) {
                MovioText(
                    text = if (isExpanded) "Read Less" else "Read More",
                    color = Theme.color.brand.primary,
                    textStyle = Theme.textStyle.label.smallRegular14
                )
            }
        }
    }
}

private fun shouldShowReadMore(text: String, maxLines: Int): Boolean {
    val lineCount = text.lines().size
    return lineCount > maxLines || text.length > 200 // Also consider long single-line text
}