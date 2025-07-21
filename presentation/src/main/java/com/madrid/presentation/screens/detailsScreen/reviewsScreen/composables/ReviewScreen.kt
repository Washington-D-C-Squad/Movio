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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.theme.MovioTheme
import com.madrid.presentation.screens.detailsScreen.similarMovies.SimilarMoviesSection

@Composable
fun ReviewScreen(
    onSeeAllReviews: () -> Unit = {},
    onSeeAllSimilarMovies: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        SectionHeader(
            title = "Reviews",
            onSeeAllClick = onSeeAllReviews
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(start = 16.dp, end = 16.dp)
                .height(137.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ReviewCard(
                reviewerName = "Awkwafina",
                reviewerImageUrl = "https://image.tmdb.org/t/p/w500/5xKGk6q5g7mVmg7k7U1RrLSHwz6.jpg",
                rating = 4.5f,
                date = "June 14, 2025",
                content = "This isn't a film, it's a live action video game with a predictable plot and loads of energetically choreographed CGI to substitute for anything vaguely akin to emotion."
            )
            ReviewCard(
                reviewerName = "Cindy",
                reviewerImageUrl = "https://image.tmdb.org/t/p/w500/5xKGk6q5g7mVmg7k7U1RrLSHwz6.jpg",
                rating = 4.5f,
                date = "June 14, 2025",
                content = "Halle Bailey performance is a passion, it shines in her voice, in her..."
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ReviewScreenPreview() {
    MovioTheme {
        ReviewScreen()
    }
}
