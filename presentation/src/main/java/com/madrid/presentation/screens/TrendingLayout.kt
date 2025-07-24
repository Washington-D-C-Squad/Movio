package com.madrid.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.madrid.presentation.component.movioCards.TrendingMovieCard
import com.madrid.presentation.viewModel.trendingViewModel.TrendingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TrendingLayout(
    modifier: Modifier = Modifier,
    trendingViewModel: TrendingViewModel = koinViewModel()
) {
    val uiState by trendingViewModel.state.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            uiState.errorMessage.isNotEmpty() -> {
                Text(
                    text = uiState.errorMessage,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                LazyHorizontalGrid(
                    rows = GridCells.Fixed(3),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(324.dp),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(uiState.trending.take(9)) { item ->
                        TrendingMovieCard(
                            imgUrl = item.posterPath,
                            movieTitle = item.title,
                            movieCategory = item.mediaType,
                            rating = item.voteAverage.toString(),
                            modifier = Modifier
                                .height(324.dp)
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}