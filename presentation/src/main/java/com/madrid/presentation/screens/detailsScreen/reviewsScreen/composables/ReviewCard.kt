package com.madrid.presentation.screens.detailsScreen.reviewsScreen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.theme.Theme
import com.madrid.designSystem.R
import com.madrid.designSystem.component.MovioIcon
import com.madrid.designSystem.component.MovioText
import com.madrid.detectImageContent.FilteredImage

@Composable
fun ReviewCard(
    reviewUiState: com.madrid.presentation.screens.detailsScreen.reviewsScreen.composables.ReviewUiState
) {
    Column(
        modifier = Modifier
            .width(258.dp)
            .height(137.dp)
            .background(
                color = Theme.color.surfaces.surfaceContainer,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(12.dp),
                color = Theme.color.surfaces.onSurfaceAt3
            )
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FilteredImage(
                imageUrl = reviewUiState.reviewerImageUrl,
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(32.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                MovioText(
                    text = reviewUiState.reviewerName,
                    color = Theme.color.surfaces.onSurface,
                    textStyle = Theme.textStyle.title.mediumMedium14
                )
                MovioText(
                    text = reviewUiState.date,
                    color = Theme.color.surfaces.onSurfaceContainer,
                    textStyle = Theme.textStyle.body.smallRegular10
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                MovioIcon(
                    painter = androidx.compose.ui.res.painterResource(id = R.drawable.bold_star),
                    contentDescription = null,
                    tint = Theme.color.system.warning,
                    modifier = Modifier.size(16.dp)
                )
                MovioText(
                    text = reviewUiState.rating.toString(),
                    color = Theme.color.system.onWarning,
                    textStyle = Theme.textStyle.label.smallRegular14
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        MovioText(
            text = reviewUiState.content,
            color = Theme.color.surfaces.onSurfaceVariant,
            textStyle = Theme.textStyle.label.smallRegular12,
            maxLines = 4,
            modifier = Modifier.fillMaxWidth()
        )
    }
}