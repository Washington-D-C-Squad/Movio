package com.madrid.presentation.screens.detailsScreen.reviewsScreen.composables

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

@Composable
fun ReviewScreen(
    uiState: ReviewsScreenUiState,
    onSeeAllReviews: () -> Unit = {},
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        SectionHeader(
            title = stringResource(id = R.string.reviews),
            onSeeAllClick = onSeeAllReviews
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (uiState.reviews.isEmpty()) {
            EmptyReviewsMessage()
        } else {
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

@Composable
private fun EmptyReviewsMessage() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        MovioText(
            text = stringResource(R.string.no_reviews_available),
            color = Theme.color.surfaces.onSurfaceVariant,
            textStyle = Theme.textStyle.body.mediumMedium14
        )
    }
}