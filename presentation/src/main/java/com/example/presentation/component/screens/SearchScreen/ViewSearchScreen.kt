package com.example.presentation.component.screens.SearchScreen


import VerticalMovioCard
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.designsystem.AppTheme
import com.example.designsystem.R
import com.example.designsystem.component.MovioIcon
import com.example.designsystem.component.MovioText
import com.example.presentation.component.viewModels.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ViewSearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    ContentSearchScreen(
        forYouMovies = uiState.forYouMovies,
        exploreMoreMovies = uiState.exploreMoreMovies,
        onSearchQueryChange = { query ->
            viewModel.searchMovies(query)
        },
        onMovieClick = { movie ->
            viewModel.navigateToMovieDetails(movie.id)
        },
        modifier = modifier
    )

    uiState.errorMessage?.let { errorMsg ->
        LaunchedEffect(errorMsg) {
            // handle error
        }
    }

    if (uiState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            MovioIcon(
                painter = painterResource(R.drawable.loading),
                contentDescription = "Loading",
                tint = AppTheme.colors.brandColors.primary
            )
        }
    }
}

@Composable
fun ContentSearchScreen(
    forYouMovies: List<Movie> = emptyList(),
    exploreMoreMovies: List<Movie> = emptyList(),
    onSearchQueryChange: (String) -> Unit = {},
    onMovieClick: (Movie) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    AppTheme.colors.surfaceColor.surfaceContainer,
                    RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                MovioIcon(
                    painter = painterResource(R.drawable.bold_search_normal),
                    contentDescription = "Search Icon",
                    tint = AppTheme.colors.surfaceColor.onSurface
                )
                BasicTextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                        onSearchQueryChange(it)
                    },
                    singleLine = true,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    decorationBox = { innerTextField ->
                        if (searchQuery.isEmpty()) {
                            MovioText(
                                text = "Search...",
                                color = AppTheme.colors.surfaceColor.onSurface_3,
                                textStyle = AppTheme.textStyle.body.medium14
                            )
                        }
                        innerTextField()
                    }
                )
            }
        }

        // For You Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MovioText(
                text = "For you",
                color = AppTheme.colors.surfaceColor.onSurface,
                textStyle = AppTheme.textStyle.headLine.medium18
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                MovioText(
                    text = "See all",
                    color = AppTheme.colors.surfaceColor.onSurface_2,
                    textStyle = AppTheme.textStyle.body.medium14
                )
                MovioIcon(
                    painter = painterResource(R.drawable.outline_alt_arrow_left),
                    contentDescription = "See all arrow",
                    tint = AppTheme.colors.surfaceColor.onSurface_2,
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .clickable {}
                )
            }
        }

        if (forYouMovies.isEmpty()) {
            MovioText(
                text = "No movies found",
                color = AppTheme.colors.surfaceColor.onSurface_3,
                textStyle = AppTheme.textStyle.body.medium14,
                modifier = Modifier.padding(bottom = 24.dp)
            )
        } else {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(bottom = 24.dp)
            ) {
                items(forYouMovies) { movie ->
                    VerticalMovioCard(
                        description = movie.title,
                        movieImage = movie.imageUrl,
                        rate = movie.rating,
                        width = 160.dp,
                        height = 200.dp,
                        paddingvalue = 8.dp,
                        onClick = { onMovieClick(movie) }
                    )
                }
            }
        }

        MovioText(
            text = "Explore more",
            color = AppTheme.colors.surfaceColor.onSurface,
            textStyle = AppTheme.textStyle.headLine.medium18,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(forYouMovies) { movie ->
                VerticalMovioCard(
                    description = movie.title,
                    movieImage = movie.imageUrl,
                    rate = movie.rating,
                    width = 160.dp,
                    height = 200.dp,
                    paddingvalue = 8.dp,
                    onClick = { onMovieClick(movie) }
                )
            }
        }
    }
}

data class Movie(
    val id: String,
    val title: String,
    val imageUrl: String,
    val rating: String,
    val category: String
)



interface MovieRepository {
    suspend fun getForYouMovies(): List<Movie>
    suspend fun getExploreMoreMovies(): List<Movie>
    suspend fun searchMovies(query: String): List<Movie>
}
