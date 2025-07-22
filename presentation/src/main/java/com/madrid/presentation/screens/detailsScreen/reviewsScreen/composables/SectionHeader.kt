package com.madrid.presentation.screens.detailsScreen.reviewsScreen.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.component.MovioText
import com.madrid.designSystem.theme.MovioTheme
import com.madrid.designSystem.theme.Theme
import androidx.compose.foundation.Image
import androidx.compose.ui.res.stringResource
import com.madrid.designSystem.R

@Composable
fun SectionHeader(
    title: String,
    onSeeAllClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .height(19.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MovioText(
            text = title,
            color = Theme.color.surfaces.onSurface,
            textStyle = Theme.textStyle.headline.mediumMedium16,
            modifier = Modifier.weight(1f)
        )
        Row(
            modifier = Modifier.clickable(onClick = onSeeAllClick),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MovioText(
                text = stringResource(id = com.madrid.presentation.R.string.see_all),
                color = Theme.color.surfaces.onSurfaceVariant,
                textStyle = Theme.textStyle.label.smallRegular14
            )
            Image(
                painter = painterResource(id = R.drawable.outline_alt_arrow_left),
                contentDescription = stringResource(id = com.madrid.presentation.R.string.see_all),
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SectionHeaderPreview() {
    MovioTheme {
        SectionHeader(
            title = "Reviews",
            onSeeAllClick = {}
        )
    }
} 