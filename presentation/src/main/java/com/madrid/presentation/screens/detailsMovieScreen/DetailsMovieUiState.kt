package com.madrid.presentation.screens.detailsMovieScreen

import com.madrid.domain.entity.Artist
import com.madrid.domain.entity.Cast

data class DetailsMovieUiState(
    val isLoved : Boolean = false,
    val topImageUrl : String = "",
    val movieName : String ="",

    val genreMovie : List<String>  = emptyList(),
    val rate : String = "",
    val movieDuration : String = "",
    val dataMovie  : String = "",

    val isRated: Boolean = false,
    val isAddedToList : Boolean = false,

    val description : String = "",
    val casts : List<Cast> = emptyList(),
    val reviews : List<Review> = emptyList(),

    val similarMovies : List<Movie> = emptyList()

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
data class Season(
    val imageUrl : String ="",
    val countOfSeason : Int  = 0 ,

    val publicSeasonData : String = "",
    val countOfEpisode : Int = 0 ,

    val description : String = "",

)