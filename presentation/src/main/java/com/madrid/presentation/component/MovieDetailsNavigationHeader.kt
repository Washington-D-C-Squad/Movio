package com.madrid.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.component.MovioIcon

@Composable
fun MovieDetailsNavigationHeader(
    onBackClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
    onHeartClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var isLiked by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = Color.Black.copy(alpha = 0.3f),
                        shape = CircleShape
                    )
                    .clickable { onBackClick() },
                contentAlignment = Alignment.Center
            ) {
                MovioIcon(
                    painter = painterResource(com.madrid.designsystem.R.drawable.arrow_left),
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = Color.Black.copy(alpha = 0.3f),
                        shape = CircleShape
                    )
                    .clickable { onShareClick() },
                contentAlignment = Alignment.Center
            ) {
                MovioIcon(
                    painter = painterResource(com.madrid.designsystem.R.drawable.outline_share),
                    contentDescription = "Share",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = Color.Black.copy(alpha = 0.3f),
                        shape = CircleShape
                    )
                    .clickable {
                        isLiked = !isLiked
                        onHeartClick()
                    },
                contentAlignment = Alignment.Center
            ) {
                MovioIcon(
                    painter = painterResource(
                        if (isLiked) com.madrid.designsystem.R.drawable.bold_heart
                        else com.madrid.designsystem.R.drawable.outline_heart
                    ),
                    contentDescription = "Like",
                    tint = if (isLiked) Color.Red else Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsNavigationHeaderPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
        ) {
            MovieDetailsNavigationHeader()
        }
    }
}