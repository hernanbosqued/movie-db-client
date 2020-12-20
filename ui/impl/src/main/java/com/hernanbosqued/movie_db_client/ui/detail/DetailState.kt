package com.hernanbosqued.movie_db_client.ui.detail

import com.hernanbosqued.movie_db_client.domain.VideoResultModel

sealed class DetailState {
    data class Ranking(val ranking: String) : DetailState()
    data class Poster(val poster: String) : DetailState()
    data class Video(val data: VideoResultModel) : DetailState()
    data class Message(val message: String) : DetailState()
}
