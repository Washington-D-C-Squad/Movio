package com.madrid.presentation.screens.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.component.FilterBar
import com.madrid.designSystem.component.TopAppBar
import com.madrid.designSystem.theme.Theme
import com.madrid.presentation.component.movioCards.MovioVerticalCard

@Composable
fun TopRatingScreen(
//    movies: ,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface)
            .statusBarsPadding(),
        contentPadding = PaddingValues(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item(
            span = { GridItemSpan(maxLineSpan) }
        ) {
            TopAppBar(
                text = "Top Rating",
                modifier = Modifier,
                secondIcon = null,
                thirdIcon = null,
            )
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            val items = listOf(
                "All",
                "Action",
                "Animation",
                "Crime",
                "fcyvbhinj",
                "ytvyubipo",
                "rdtyvbuin",
                "fvghnjkm,l",
                "tyvubomkp,l"
            )
            var selectedItem by remember { mutableStateOf(items.first()) }

            FilterBar(
                items = items,
                selectedItem = selectedItem,
                onItemClick = { selectedItem = it },
                scrollable = true
            )
        }
        items(count = movies.itemCount) { index ->
            MovioVerticalCard(
                description = movies[index]!!.title,
                movieImage = movies[index]!!.imageUrl,
                rate = movies[index]!!.rating,
                width = 101.dp,
                height = 136.dp,
                onClick = { }
            )
        }
    }
}


@Preview
@Composable
private fun TopRatingScreenPreview(modifier: Modifier = Modifier) {
//    TopRatingScreen()
}