package com.madrid.presentation.viewModel.detailsViewModel

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.madrid.domain.entity.Review
import com.madrid.domain.entity.SimilarSeries
import com.madrid.domain.usecase.mediaDeatailsUseCase.SeriesDetailsUseCase
import com.madrid.presentation.navigation.Destinations
import com.madrid.presentation.viewModel.base.BaseViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SeriesDetailsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val seriesDetailsUseCase: SeriesDetailsUseCase
) : BaseViewModel<SeriesDetailsUiState, Nothing>(SeriesDetailsUiState()) {
    private val args = savedStateHandle.toRoute<Destinations.SeriesDetailsScreen>()

    init {
        Log.d("loool", ": ")
        loadData()
    }

    private fun loadData() {
        tryToExecute(
            function = { seriesDetailsUseCase.getSeriesDetailsById(args.seriesId) },
            onSuccess = { series ->
                updateState {
                    it.copy(
                        seriesId = series.id,
                        topImageUrl = series.imageUrl,
                        seriesName = series.title,
                        rate = series.rate.toString(),
                        numberOfSeasons = series.seasons.size,
                        productionDate = series.yearOfRelease,
                        description = series.description,
                        currentSeasonsUiStates = series.seasons.map { season -> season.mapToUiState() },
                        selectedSeasonUiState = series.seasons[args.seasonNumber - 1].mapToUiState()
                    )
                }
                loadAllSeasonsEpisodes()
                loadCastData()
                loadReviews()
                loadSimilarSeries()
            },
            onError = {},
        )
        loadSeasonEpisodes(args.seasonNumber)
    }

    private fun loadAllSeasonsEpisodes() {
        viewModelScope.launch {
            val seasonCount = state.first().numberOfSeasons
            Log.d("TAG lol", "loadAllSeasonsEpisodes: ${state.first().numberOfSeasons}")
            for (i in 0..seasonCount) {
                tryToExecute(
                    function = { seriesDetailsUseCase.getEpisodesBySeriesId(args.seriesId, i + 1) },
                    onSuccess = { episodes ->
                        updateState { currentState ->
                            currentState.copy(
                                currentSeasonsUiStates = currentState.currentSeasonsUiStates.mapIndexed { index, season ->
                                    if (index == i) {
                                        season.copy(
                                            numberOfEpisodes = episodes.size,
                                            episodesUiStates = episodes.map { episode -> episode.toUiState() }
                                        )
                                    } else {
                                        season
                                    }
                                }
                            )
                        }
                    },
                    onError = {}
                )
            }
        }
    }

    fun updateSelectedSeason(seasonNumber: Int) = loadSeasonEpisodes(seasonNumber)

    private fun loadSeasonEpisodes(seasonNumber: Int = 1) {
        tryToExecute(
            function = { seriesDetailsUseCase.getEpisodesBySeriesId(args.seriesId, seasonNumber) },
            onSuccess = { episodes ->
                updateState {
                    it.copy(selectedSeasonUiState = it.selectedSeasonUiState.copy(episodesUiStates = episodes.map { episode ->
                        episode.toUiState()
                    }, numberOfEpisodes = episodes.size, seasonNumber = seasonNumber))
                }
            },
            onError = { },
        )
    }

    private fun loadCastData() {
        tryToExecute(
            function = {
                seriesDetailsUseCase.getSeriesCreditsById(args.seriesId)
            },
            onSuccess = { Artists ->
                updateState {
                    it.copy(topCast = Artists.map { artist ->
                        artist.mapToUiState()
                    })
                }
            },
            onError = {e ->
                Log.d("TAG lol", "loadCastData: ${e.message}")
            },
        )
    }

    private fun loadReviews(){
        tryToExecute(
            function = {
                seriesDetailsUseCase.getSeriesReviewsById(args.seriesId)
            },
            onSuccess = { reviews ->
                updateState {
                    it.copy(reviews = reviews.map { review ->
                        review.toUiState()
                    })
                }
            },
            onError = {e ->
                Log.d("TAG lol", "loadCastData: ${e.message}")
            },
        )
    }

    private fun loadSimilarSeries(){
        tryToExecute(
            function = {
                seriesDetailsUseCase.getSimilarSeriesById(args.seriesId)
            },
            onSuccess = { allSeries ->
                updateState {
                    it.copy(similarSeries = allSeries.map { series ->
                        series.toUiState()
                    })
                }
            },
            onError = {e ->
                Log.d("TAG lol", "loadCastData: ${e.message}")
            },
        )
    }
}

fun SimilarSeries.toUiState(): SeriesUiState{
    return SeriesUiState(
        id = this.id,
        name = this.title,
        imageUrl = this.imageUrl,
        rate = this.rate.toString()
    )
}


fun Review.toUiState(): ReviewUiState{
    return ReviewUiState(
        reviewerName = "Anonymous" ,
        reviewerImageUrl = "",
        rating = this.rate.toFloat(),
        date = this.dateOfRelease,
        content = this.comment
    )
}

