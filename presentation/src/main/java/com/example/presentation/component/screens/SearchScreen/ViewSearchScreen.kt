package com.example.presentation.component.screens.SearchScreen


import VerticalMovioCard
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.designsystem.R
import com.example.presentation.component.viewmodel.SearchViewModel
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

        // هنا مجرد مثال سريع:
        LaunchedEffect(errorMsg) {

        }
    }


    if (uiState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
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
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                onSearchQueryChange(it)
            },
            placeholder = { Text("Search...") },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.bold_search_normal),
                    contentDescription = "Search Icon"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .clickable { },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.LightGray
            )
        )

        // For You Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "For you",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Black
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "See all",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Icon(
                   // modifier = Modifier.clickable{},
                    painter = painterResource(R.drawable.outline_alt_arrow_left),
                    contentDescription = "See all arrow",
                    modifier = Modifier.padding(start = 4.dp)
                        .clickable{},
                    tint = Color.Gray
                )
            }
        }


        if (forYouMovies.isEmpty()) {
            Text(
                text = "No movies found",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
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


        Text(
            text = "Explore more",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold
            ),
            color = Color.Black,
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
}}

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
