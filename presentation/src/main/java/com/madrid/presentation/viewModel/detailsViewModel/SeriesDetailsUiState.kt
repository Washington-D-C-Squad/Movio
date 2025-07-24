package com.madrid.presentation.viewModel.detailsViewModel

data class SeriesDetailsUiState(
    val seriesId: Int = 0,
    val isFavourite: Boolean = false,
    val topImageUrl: String = "",
    val seriesName: String = "",
    val seriesGenre: List<String> = emptyList(),
    val rate: String = "",
    val numberOfSeasons: Int = 0,
    val productionDate: String = "",
    val isRated: Boolean = false,
    val isAddedToList: Boolean = false,
    val description: String = "",
    val topCast: List<Artist> = emptyList(),
    val currentSeasonsUiStates: List<SeasonUiState> = emptyList(),
    val reviews: List<Review> = emptyList(),
    val similarSeries: List<Series> = emptyList(),
    val selectedSeasonUiState: SeasonUiState = SeasonUiState(),
)

data class Series(
    val id: Int = 0,
    val imageUrl: String = "",
    val rate: String = "",
    val name: String = ""
)

data class Artist(
    val id: Int,
    val imageUrl: String,
    val name: String
)

data class SeasonUiState(
    val id: Int = 0,
    val imageUrl: String = "",
    val seasonNumber: Int = 0,
    val productionDate: String = "",
    val numberOfEpisodes: Int = 0,
    val rate: String = "",
    val description: String = "",
    val episodesUiStates: List<EpisodeUiState> = listOf()
)

data class EpisodeUiState(
    val imageUrl: String = "",
    val episodeName: String = "",
    val episodeNumber: Int = 0,
    val episodeDuration: String = "",
    val rate: String = "",
)
