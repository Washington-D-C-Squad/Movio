package com.madrid.presentation.screens.detailsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.component.MovioText
import com.madrid.designsystem.component.TopAppBar
import com.madrid.presentation.composables.ActorDetailsHeader
import com.madrid.presentation.composables.movieActorBackround.MoviePosterDetailScreen
import com.madrid.presentation.composables.movioCards.MovioVerticalCard

@Composable
fun ActorDetails(
    actorImageUrl: String,
    descrptoin: String,
) {
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.surfaceColor.surface),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item(
        ) {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                TopAppBar(
                    text = null,
                    secondIcon = null,
                    thirdIcon = null,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .statusBarsPadding()
                )
                MoviePosterDetailScreen(
                    ImageUrl = actorImageUrl,
                    isActor = true
                )
            }
        }
        item() {
            ActorDetailsHeader(
                actorName = "tom holland",
                actorRole = "acting",
                dateOfBirth = "September 2, 1964",
                Location = "location",
            )
        }
        item() {
            MovioText(
                text = descrptoin,
                color = AppTheme.colors.surfaceColor.onSurface,
                textStyle = AppTheme.textStyle.label.smallRegular14,
                maxLines = 5,
                modifier = Modifier.padding( horizontal = 16.dp ).padding(bottom = 16.dp)
            )
        }
        item() {
            MovioText(
                text = "Known For",
                color = AppTheme.colors.surfaceColor.onSurface,
                textStyle = AppTheme.textStyle.title.medium16,
                modifier = Modifier.padding(horizontal = 16.dp )
            )
        }
        item(
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .padding(
                        bottom = AppTheme.spacing.xLarge
                    )
                    .height(333.dp),
            ) {
                items(6) { movie ->
                    MovioVerticalCard(
                        description ="spider man",
                        movieImage = "data:image/jpeg;base64,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",
                        rate = "5.0",
                        width = 124.dp,
                        height = 300.dp,
                        paddingvalue = AppTheme.spacing.small,
                        onClick = {  },
                        modifier = Modifier.navigationBarsPadding().padding(vertical = 12.dp)
                    )
                }
            }
        }
    }
}