package com.madrid.presentation.viewModel.detailsViewModel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.madrid.domain.usecase.mediaDeatailsUseCase.SeriesDetailsUseCase
import com.madrid.presentation.navigation.Destinations
import com.madrid.presentation.viewModel.base.BaseViewModel

class SeriesDetailsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val seriesDetailsUseCase: SeriesDetailsUseCase
) : BaseViewModel<SeriesDetailsUiState, Nothing>(SeriesDetailsUiState()) {
    private val args = savedStateHandle.toRoute<Destinations.SeriesDetailsScreen>()

    init {
        Log.d("loool", ": ")
        loadData()
        loadCastData()
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
                        numberOfSeasons = series.seasons.size ,
                        productionDate = series.yearOfRelease,
                        description = series.description,
                        currentSeasonsUiStates = series.seasons.map { season -> season.mapToUiState() },
                        selectedSeasonUiState = series.seasons[args.seasonNumber -1].mapToUiState()
                    )
                }
            },
            onError = {},
        )
        loadSeasonEpisodes(args.seasonNumber)
    }

    fun updateSelectedSeason(seasonNumber: Int) = loadSeasonEpisodes(seasonNumber)

    private fun loadSeasonEpisodes(seasonNumber: Int = 1) {
        tryToExecute(
            function = { seriesDetailsUseCase.getEpisodesBySeriesId(args.seriesId, seasonNumber) },
            onSuccess = { episodes ->
                updateState {
                    it.copy(selectedSeasonUiState = it.selectedSeasonUiState.copy(episodesUiStates = episodes.map { episode ->
                        episode.toUiState()
                    }, numberOfEpisodes = episodes.size , seasonNumber = seasonNumber))
                }
            },
            onError = { },
        )
    }

    private fun loadCastData() {
        tryToExecute(
            function = { seriesDetailsUseCase.getSeriesCreditsById(args.seriesId) },
            onSuccess = { Artists ->
                updateState {
                    it.copy(topCast = Artists.map { artist ->
                        artist.mapToUiState()
                    })
                }
            },
            onError = { },
        )
    }
}

