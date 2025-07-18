package com.madrid.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.component.MovioText

@Composable
fun ActorDetailsHeader(
    actorName: String,
    actorRole: String,
    dateOfBirth: String,
    Location: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        MovioText(
            actorName,
            color = AppTheme.colors.surfaceColor.onSurface,
            textStyle = AppTheme.textStyle.headLine.medium18
        )
        MovioText(
            actorRole,
            color = AppTheme.colors.surfaceColor.onSurfaceVariant,
            textStyle = AppTheme.textStyle.label.smallRegular14
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Chips(
                icon = painterResource(com.madrid.designsystem.R.drawable.outline_calendar),
                iconTint = AppTheme.colors.surfaceColor.onSurfaceVariant,
                text = dateOfBirth,
            )
            Chips(
                icon = painterResource(com.madrid.designsystem.R.drawable.outline_map_point),
                iconTint = AppTheme.colors.surfaceColor.onSurfaceVariant,
                text = Location,
            )
        }
    }
}