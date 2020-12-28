package com.hernanbosqued.movie_db_client.ui.detail

import com.hernanbosqued.movie_db_client.domain.EpisodeModel
import com.hernanbosqued.movie_db_client.domain.SeasonModel

sealed class DetailState {
    data class Ranking(val ranking: String) : DetailState()
    data class Poster(val poster: String) : DetailState()
    data class Message(val message: String) : DetailState()
    data class Seasons(val seasons: List<SeasonModel>) : DetailState()
    data class Episodes(val episodes: List<EpisodeModel>) : DetailState()
}
