package com.madrid.presentation.screens.reviewsScreen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.R
import com.madrid.designsystem.component.MovioIcon
import com.madrid.designsystem.component.MovioText

@Composable
fun ReviewCard(
    reviewerName: String,
    reviewerImageUrl: String,
    rating: Float,
    date: String,
    content: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = AppTheme.colors.surfaceColor.surfaceContainer,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(8.dp),
                color = AppTheme.colors.surfaceColor.onSurface_3
            )
            .padding(12.dp)

    ) {

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row {
                AsyncImage(
                    model = reviewerImageUrl,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clip(CircleShape)
                        .size(32.dp)

                )
                Column(
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    MovioText(
                        text = reviewerName,
                        color = AppTheme.colors.surfaceColor.onSurface,
                        textStyle = AppTheme.textStyle.title.medium14,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    MovioText(
                        text = date,
                        color = AppTheme.colors.surfaceColor.onSurfaceContainer,
                        textStyle = AppTheme.textStyle.body.smallRegular10,
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        MovioIcon(
                            painter = painterResource(id = R.drawable.bold_star),
                            contentDescription = "",
                            tint = AppTheme.colors.systemColors.warning,
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .size(16.dp)
                        )
                        MovioText(
                            text = rating.toString(),
                            color = AppTheme.colors.systemColors.onWarning,
                            textStyle = AppTheme.textStyle.label.smallRegular12
                        )
                    }
                }
            }

        }
        MovioText(
            text = content,
            color = AppTheme.colors.surfaceColor.onSurfaceVariant,
            textStyle = AppTheme.textStyle.label.smallRegular12,
            maxLines = 20,
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}
