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
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.component.MovioText
import com.madrid.designsystem.component.TopAppBar
import com.madrid.presentation.composables.ActorDetailsHeader
import com.madrid.presentation.composables.movieActorBackround.MoviePosterDetailScreen
import com.madrid.presentation.composables.movioCards.MovioVerticalCard
import com.madrid.presentation.screens.searchScreen.SearchResultMessage
import com.madrid.presentation.screens.searchScreen.SearchScreenState
import com.madrid.presentation.screens.searchScreen.viewModel.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ActorDetailsC(viewModel: SearchViewModel= koinViewModel()) {

    val uiState by viewModel.state.collectAsState()
    ActorDetailsContent(
        actorImageUrl = "",
        descrptoin = "fcysuyiu cnwoiaicn kjvnaniasjfllkncxjvniwajINCJncwonAODJVBo'BNC0 onicnzbfvuwabojnspk nuv[0wha]nnc ",
        actorName = "tom ",
        actorRole = "actor",
        dateOfBirth = "2000/2/2",
        Location = "location",
        movies = uiState.filteredScreenUiState.movie,
        viewModel = viewModel
    )
}
@Composable
fun ActorDetailsContent(
    actorImageUrl: String,
    descrptoin: String,
    actorName:String,
    actorRole:String,
    dateOfBirth:String,
    Location:String,
    movies: List<com.madrid.presentation.screens.searchScreen.viewModel.SearchScreenState.MovieUiState>,
    viewModel:SearchViewModel
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
                actorName = actorName,
                actorRole = actorRole,
                dateOfBirth = dateOfBirth,
                Location = Location,
            )
        }
        item() {
            MovioText(
                text = descrptoin,
                color = AppTheme.colors.surfaceColor.onSurface,
                textStyle = AppTheme.textStyle.label.smallRegular14,
                maxLines = 5,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
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
                items(
                    count = movies.size,

                    ) { index ->
                    MovioVerticalCard(
                        description = movies[index].title,
                        movieImage =  movies[index].imageUrl,
                        rate =  movies[index].rating,
                        width = 100.dp,
                        height = 178.dp,
                        onClick = { /* onMovieClick(movie.title) */ },
                        modifier = Modifier .navigationBarsPadding()
                            .padding(vertical = 12.dp)
                    )
                }
            }
        }
    }
}