package com.madrid.presentation.component.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.theme.Theme
import com.madrid.designsystem.R
import com.madrid.designsystem.component.MovioText
import com.madrid.presentation.component.DetailsChips

@Composable
fun MovieDetailsHeader(
    movieName: String,
    movieCategory: List<String>,
    date: String,
    time: String,
    rate: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        MovioText(
            movieName,
            color = Theme.color.surfaces.onSurface,
            textStyle = Theme.textStyle.headline.mediumMedium18
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
        ) {
            movieCategory.forEach {
                MovioText(
                    if (it != movieCategory[movieCategory.lastIndex]) it + "," else it,
                    color = Theme.color.surfaces.onSurfaceVariant,
                    textStyle = Theme.textStyle.label.smallRegular14
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Chips(
                icon = painterResource(com.madrid.designSystem.R.drawable.bold_star),
                iconTint = Theme.color.system.warning,
                text = rate,
            )
            Chips(
                icon = painterResource(com.madrid.designSystem.R.drawable.outline_clock_circle),
                iconTint = Theme.color.surfaces.onSurfaceVariant,
                text = time,
            )
            Chips(
                icon = painterResource(com.madrid.designSystem.R.drawable.outline_calendar),
                iconTint = Theme.color.surfaces.onSurfaceVariant,
                text = date,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TopAppBarPreview() {
    AppTheme {
        MovieDetailsHeader(
            movieName = "spider man",
            movieCategory = listOf("action", "drama", "comedy"),
            date = "2020/10/10",
            time = "44",
            rate = "4.5",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        )
    }
}