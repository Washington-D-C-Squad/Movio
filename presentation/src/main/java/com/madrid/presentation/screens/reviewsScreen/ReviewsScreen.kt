package com.madrid.presentation.screens.reviewsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.R
import com.madrid.designsystem.component.MovioIcon
import com.madrid.designsystem.component.MovioText
import com.madrid.presentation.screens.reviewsScreen.composables.ReviewCard

@Composable
fun ReviewsScreen() {

    ReviewsScreenContent(
        reviewsScreenUiState = ReviewsScreenUiState(
            reviews = emptyList()
        )
    )
    //TODO("change UIState that is passed to the screen content")

}

@Composable
fun ReviewsScreenContent(
    reviewsScreenUiState: ReviewsScreenUiState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = AppTheme.colors.surfaceColor.surface
            )
            .padding(horizontal = 16.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) {
            MovioIcon(
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = "Back",
                tint = AppTheme.colors.surfaceColor.onSurface,
                modifier = Modifier
                    .padding(
                        start = AppTheme.spacing.xLarge,
                    )
                    .align(Alignment.CenterStart)
            )
            MovioText(
                text = "Reviews",
                color = AppTheme.colors.surfaceColor.onSurface,
                textStyle = AppTheme.textStyle.headLine.largeBold16,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = 14.5.dp)
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            itemsIndexed(reviewsScreenUiState.reviews) { index, item ->
                ReviewCard(
                    item.reviewerName,
                    item.reviewerImageUrl,
                    item.rating,
                    item.date,
                    item.content
                )
            }
        }
    }
}
