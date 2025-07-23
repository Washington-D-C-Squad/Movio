package com.madrid.presentation.viewModel.detailsViewModel

import com.madrid.domain.entity.Cast
import com.madrid.domain.entity.Episode
import com.madrid.domain.entity.Season


fun Episode.toUiState(): EpisodeUiState {
    return EpisodeUiState(
        imageUrl = this.imageUrl,
        episodeName = this.title,
        episodeNumber = this.episodeNumber,
        episodeDuration = this.duration,
        rate = this.rate.toString(),
    )
}

fun Season.mapToUiState(): SeasonUiState {
    return SeasonUiState(
        id = this.id,
        imageUrl = this.imageUrl,
        seasonNumber = this.seasonNumber,
        productionDate = this.yearOfRelease,
        rate = this.rate.toString(),
        description = this.description,
    )
}

fun Cast.mapToUiState(): Artist {
    return Artist(
        id = this.id,
        imageUrl = this.imageUrl,
        name = this.name
    )
}