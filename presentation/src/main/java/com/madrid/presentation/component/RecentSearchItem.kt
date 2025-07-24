package com.madrid.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.R
import com.madrid.designSystem.component.MovioAnnotatedText
import com.madrid.designSystem.component.MovioButton
import com.madrid.designSystem.component.MovioIcon
import com.madrid.designSystem.theme.Theme

@Composable
fun RecentSearchItem(
    searchText: String,
    searchQuery: String,
    onItemClick: () -> Unit,
    onRemoveClick: () -> Unit,
    highlightCharactersInText: (String, String, Color, Color, TextStyle) -> AnnotatedString,
) {
    val highlightedText = highlightCharactersInText(
        searchText,
        searchQuery,
        Theme.color.surfaces.onSurface,
        Theme.color.surfaces.onSurfaceVariant,
        Theme.textStyle.body.mediumMedium14
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        MovioIcon(
            painter = painterResource(id = R.drawable.outline_clock_circle),
            contentDescription = stringResource(id = com.madrid.presentation.R.string.clock_content_description),
            tint = Theme.color.surfaces.onSurfaceVariant,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        MovioAnnotatedText(
            annotatedText = highlightedText,
            textStyle = Theme.textStyle.label.smallRegular14,
            color = Theme.color.surfaces.onSurfaceVariant,
            modifier = Modifier.weight(1f)
        )

        MovioButton(
            onClick = onRemoveClick,
            color = Color.Transparent
        ) {
            MovioIcon(
                painter = painterResource(id = R.drawable.outline_add),
                contentDescription = stringResource(id = com.madrid.presentation.R.string.delete_content_description),
                tint = Theme.color.surfaces.onSurfaceVariant,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

