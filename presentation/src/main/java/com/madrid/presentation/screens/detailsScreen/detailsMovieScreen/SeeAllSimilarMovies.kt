package com.madrid.presentation.screens.detailsScreen.detailsMovieScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.madrid.designSystem.R
import com.madrid.designSystem.component.EmptySearchLayout
import com.madrid.designSystem.component.LoadingSearchCard
import com.madrid.designSystem.component.MovioIcon
import com.madrid.designSystem.component.MovioText
import com.madrid.designSystem.theme.Theme
import com.madrid.presentation.component.movioCards.MovioVerticalCard
import com.madrid.presentation.navigation.Destinations
import com.madrid.presentation.navigation.LocalNavController
import com.madrid.presentation.screens.detailsScreen.similarMovies.SimilarMoviesViewModel
import com.madrid.presentation.viewModel.searchViewModel.SearchScreenState

@Composable
fun SeeAllSimilarMoviesScreen(
    movieId: Int,
    viewModel: SimilarMoviesViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
    val navController = LocalNavController.current
    val uiState by viewModel.uiState.collectAsState()
    val similarMovies = uiState.similarMovies.collectAsLazyPagingItems()

    LaunchedEffect(movieId) {
        viewModel.loadSimilarMovies(movieId)
    }

    SeeAllSimilarMoviesScreenContent(
        similarMovies = similarMovies,
        onClickBackIcon = { navController.popBackStack() },
        onMovieClick = { movieId ->
            navController.navigate(Destinations.MovieDetailsScreen(movieId))
        }
    )
}

@Composable
private fun SeeAllSimilarMoviesScreenContent(
    similarMovies: LazyPagingItems<SearchScreenState.MovieUiState>,
    onClickBackIcon: () -> Unit,
    onMovieClick: (Int) -> Unit = {}
) {
    val isLoading = similarMovies.loadState.refresh is LoadState.Loading
    val isError = similarMovies.loadState.refresh is LoadState.Error
    val isEmpty = similarMovies.itemCount == 0 &&
            similarMovies.loadState.refresh is LoadState.NotLoading &&
            !similarMovies.loadState.refresh.endOfPaginationReached

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 100.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface)
            .statusBarsPadding(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Header with back button and title
        item(span = { GridItemSpan(maxLineSpan) }) {
            Row(
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MovioIcon(
                    modifier = Modifier.clickable { onClickBackIcon() },
                    painter = painterResource(R.drawable.arrow_left),
                    contentDescription = "Back",
                    tint = Theme.color.surfaces.onSurface
                )
                Spacer(Modifier.width(8.dp))
                MovioText(
                    text = "Similar Movies",
                    color = Theme.color.surfaces.onSurface,
                    textStyle = Theme.textStyle.headline.largeBold16,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }

        // Content based on state
        when {
            isLoading -> {
                items(9) {
                    LoadingSearchCard()
                }
            }

            isError -> {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 64.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        EmptySearchLayout(
                            title = "Internet is not available",
                            description = "Please make sure you are connected to the internet and try again.",
                            image = com.madrid.presentation.R.drawable.img_no_internet
                        )
                    }
                }
            }

            isEmpty -> {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 64.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        EmptySearchLayout(
                            title = "No similar movies found",
                            description = "We couldn't find any movies similar to this one. Try exploring other movies!",
                            image = com.madrid.presentation.R.drawable.img_no_sesrch_found
                        )
                    }
                }
            }

            else -> {
                items(count = similarMovies.itemCount) { index ->
                    similarMovies[index]?.let { movie ->
                        MovioVerticalCard(
                            modifier = Modifier.fillMaxWidth(),
                            description = movie.title,
                            movieImage = movie.imageUrl,
                            rate = movie.rating,
                            width = 101.dp,
                            height = 136.dp,
                            onClick = { onMovieClick(movie.id.toInt()) }
                        )
                    }
                }

                // Handle loading more items
                when (similarMovies.loadState.append) {
                    is LoadState.Loading -> {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                LoadingSearchCard()
                            }
                        }
                    }
                    is LoadState.Error -> {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                MovioText(
                                    text = "Error loading more movies",
                                    color = Theme.color.surfaces.onSurface,
                                    textStyle = Theme.textStyle.body.mediumMedium14
                                )
                            }
                        }
                    }
                    else -> {}
                }
            }
        }
    }
}