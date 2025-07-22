package com.madrid.presentation.screens.detailsScreen.reviewsScreen.composables

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.madrid.presentation.R
import com.madrid.presentation.viewModel.detailsViewModel.ReviewUiState
import com.madrid.presentation.viewModel.detailsViewModel.ReviewsScreenUiState

@Composable
fun ReviewScreen(
    uiState: ReviewsScreenUiState,
    onSeeAllReviews: () -> Unit = {},
) {
    if (uiState.reviews.isNotEmpty()) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            SectionHeader(
                title = stringResource(id = R.string.reviews),
                onSeeAllClick = onSeeAllReviews
            )

            Spacer(modifier = Modifier.height(16.dp))

            ReviewsList(reviews = uiState.reviews)
        }
    }
}

@Composable
private fun ReviewsList(reviews: List<ReviewUiState>) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp)
            .height(137.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        reviews.forEach { review ->
            ReviewCard(
                reviewerName = review.reviewerName,
                reviewerImageUrl = review.reviewerImageUrl,
                rating = review.rating,
                date = review.date,
                content = review.content
            )
        }
    }
}