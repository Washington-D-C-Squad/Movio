package com.madrid.presentation.screens.detailsScreen.reviewsScreen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.component.MovioText
import com.madrid.designSystem.theme.Theme
import com.madrid.presentation.R
import com.madrid.presentation.viewModel.detailsViewModel.Review
import com.madrid.presentation.viewModel.detailsViewModel.ReviewUiState
import com.madrid.presentation.viewModel.detailsViewModel.ReviewsScreenUiState

@Composable
fun ReviewScreen(
    reviews: List<Review>,
    uiState: ReviewsScreenUiState,
    modifier: Modifier = Modifier,
    isExpanded: Boolean = false,
    onSeeAllReviews: () -> Unit = {}
) {
    // Only show the section if there are reviews
    if (uiState.reviews.isNotEmpty()) {
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            SectionHeader(
                title = stringResource(id = R.string.reviews),
                onSeeAllClick = onSeeAllReviews
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (isExpanded) {
                ExpandedReviewsList(reviews = uiState.reviews)
            } else {
                HorizontalReviewsList(reviews = uiState.reviews.take(3))
            }
        }
    }
}

@Composable
private fun HorizontalReviewsList(reviews: List<ReviewUiState>) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
            .height(180.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        reviews.forEach { review ->
            ReviewCard(
                reviewerName = review.reviewerName,
                reviewerImageUrl = review.reviewerImageUrl,
                rating = review.rating,
                date = review.date,
                content = review.content.take(100).let {
                    if (review.content.length > 100) "$it..." else it
                }
            )
        }
    }
}

@Composable
private fun ExpandedReviewsList(reviews: List<ReviewUiState>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        reviews.forEach { review ->
            ExpandedReviewCard(
                reviewerName = review.reviewerName,
                reviewerImageUrl = review.reviewerImageUrl,
                rating = review.rating,
                date = review.date,
                content = review.content
            )
        }
    }
}

@Composable
private fun ExpandedReviewCard(
    reviewerName: String,
    reviewerImageUrl: String?,
    rating: Float,
    date: String,
    content: String
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column {
                MovioText(
                    text = reviewerName,
                    color = Theme.color.surfaces.onSurface,
                    textStyle = Theme.textStyle.title.mediumMedium14
                )
                MovioText(
                    text = date,
                    color = Theme.color.surfaces.onSurfaceContainer,
                    textStyle = Theme.textStyle.body.smallRegular10
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        MovioText(
            text = content,
            color = Theme.color.surfaces.onSurface,
            textStyle = Theme.textStyle.title.mediumMedium14
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Custom divider
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Theme.color.surfaces.outline)
        )
    }
}
