package com.madrid.presentation.screens.detailsScreen.reviewsScreen

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
import com.madrid.designSystem.R
import com.madrid.designSystem.component.MovioIcon
import com.madrid.designSystem.component.MovioText
import com.madrid.designSystem.theme.Theme
import com.madrid.presentation.screens.detailsScreen.reviewsScreen.composables.ReviewCard
import com.madrid.presentation.viewModel.detailsViewModel.ReviewsScreenUiState

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
                color = Theme.color.surfaces.surface
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
                tint = Theme.color.surfaces.onSurface,
                modifier = Modifier
                    .padding(
                        start = 24.dp,
                    )
                    .align(Alignment.CenterStart)
            )
            MovioText(
                text = "Reviews",
                color = Theme.color.surfaces.onSurface,
                textStyle = Theme.textStyle.headline.largeBold16,
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
