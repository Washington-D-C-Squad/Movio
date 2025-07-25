package com.madrid.presentation.viewModel.detailsViewModel

import com.madrid.domain.entity.Cast
import com.madrid.presentation.screens.detailsScreen.similarMedia.SimilarMovie

data class DetailsMovieUiState(
    val isLoved : Boolean = false,
    val topImageUrl : String = "",
    val movieName : String ="",
    val movieId : String ="",
    val genreMovie : List<String>  = emptyList(),
    val rate : String = "",
    val movieDuration : String = "",
    val dataMovie  : String = "",

    val isRated: Boolean = false,
    val isAddedToList : Boolean = false,

    val description : String = "",
    val casts : List<Cast> = emptyList(),
    val reviews : List<ReviewUiState> = emptyList(),

    val similarMovies : List<SimilarMovie> = emptyList()

)

data class Movie (
    val id : Int = 0 ,
    val imageUrl: String = "",
    val rate: Double = 0.0 ,
    val name : String  = ""
)

data class Review(
    val personImageUrl : String  = "",
    val personName : String = "",
    val reviewTime : String = "",
    val rate : Double  = 0.0 ,
    val reviewText : String  = ""
)
