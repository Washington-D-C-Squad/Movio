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
import com.madrid.designSystem.theme.Theme
import com.madrid.designSystem.R
import com.madrid.designSystem.component.MovioIcon
import com.madrid.designSystem.component.MovioText

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
                color = Theme.color.surfaces.surfaceContainer,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(8.dp),
                color = Theme.color.surfaces.onSurfaceAt3
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
                        color = Theme.color.surfaces.onSurface,
                        textStyle = Theme.textStyle.title.mediumMedium14,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    MovioText(
                        text = date,
                        color = Theme.color.surfaces.onSurfaceContainer,
                        textStyle = Theme.textStyle.body.smallRegular10,
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
                            tint = Theme.color.system.warning,
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .size(16.dp)
                        )
                        MovioText(
                            text = rating.toString(),
                            color = Theme.color.system.onWarning,
                            textStyle = Theme.textStyle.label.smallRegular12
                        )
                    }
                }
            }

        }
        MovioText(
            text = content,
            color = Theme.color.surfaces.onSurfaceVariant,
            textStyle = Theme.textStyle.label.smallRegular12,
            maxLines = 20,
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}
