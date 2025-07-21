package com.madrid.presentation.screens.detailsScreen.reviewsScreen.composables

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.theme.MovioTheme

@Composable
fun ReviewScreen() {
    val posterUrl = "https://image.tmdb.org/t/p/w500/5xKGk6q5g7mVmg7k7U1RrLSHwz6.jpg"
    val reviews = listOf(
        Triple(
            "Awkwafina",
            posterUrl,
            "This isn't a film, it's a live action video game with a predictable plot and loads of energetically choreographed CGI to substitute for anything vaguely akin to emotion."
        ),
        Triple(
            "Cindy",
            posterUrl,
            "Halle Bailey performance is a passion, it shines in her voice, in her..."
        )
    )
    Column(
        modifier = Modifier.padding(top = 16.dp)
    ) {
        ReviewsTopbar()
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(start = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            reviews.forEach { (name, img, content) ->
                ReviewCard(
                    reviewerName = name,
                    reviewerImageUrl = img,
                    rating = 4.5f,
                    date = "June 14, 2025",
                    content = content
                )
            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun jh(){
    MovioTheme {
        ReviewScreen()
    }
}
