package com.madrid.presentation.viewModel.detailsViewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.madrid.domain.entity.Cast
import com.madrid.domain.entity.Season
import com.madrid.domain.usecase.mediaDeatailsUseCase.SeriesDetailsUseCase
import com.madrid.domain.usecase.searchUseCase.RecentSearchUseCase
import com.madrid.presentation.navigation.Destinations
import com.madrid.presentation.viewModel.base.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SeriesDetailsViewModel(
    override val recentSearchUseCase: RecentSearchUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val seriesDetailsUseCase: SeriesDetailsUseCase
) : BaseViewModel<SeriesDetailsUiState>(SeriesDetailsUiState()) {
    val args = savedStateHandle.toRoute<Destinations.SeriesDetailsScreen>()

    init {
        loadData()
        loadCastData()
    }

    private fun loadData() {
        tryToExecute(
            function = { seriesDetailsUseCase.getSeriesDetailsById(args.seriesId) },
            onSuccess = { series ->
                updateState {
                    it.copy(
                        topImageUrl = series.imageUrl,
                        seriesName = series.title,
                        rate = series.rate.toString(),
                        numberOfSeasons = series.seasons.size,
                        productionDate = series.yearOfRelease,
                        description = series.description,
                        currentSeasonUiStates = series.seasons.map { season -> season.mapToUiState() }
                    )
                }
            },
            onError = {},
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
            onError = {  },
        )
    }
}


fun Season.mapToUiState(): SeasonUiState {
    return SeasonUiState(
        id = this.seasonNumber, imageUrl = this.imageUrl,
        seasonNumber = this.seasonNumber,
        productionDate = this.yearOfRelease,
        numberOfEpisodes = this.episodes.size,
        rate = this.rate.toString(),
        description = this.description
    )
}

fun Cast.mapToUiState(): Artist {
    return Artist(
        id = this.id,
        imageUrl = this.imageUrl,
        name = this.name
    )
}

