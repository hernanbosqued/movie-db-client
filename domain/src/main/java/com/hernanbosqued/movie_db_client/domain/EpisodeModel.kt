package com.hernanbosqued.movie_db_client.domain

import com.google.gson.annotations.SerializedName

class EpisodeModel {
    @SerializedName(value = "air_date")
    val airDate: String = ""

    @SerializedName(value = "episode_count")
    val episodeCount: Int = -1

    @SerializedName(value = "id")
    val id: Int = -1

    @SerializedName(value = "name")
    val name: String = ""

    @SerializedName(value = "overview")
    val overview: String = ""

    @SerializedName(value = "poster_path")
    val posterPath: String = ""

    @SerializedName(value = "season_number")
    val seasonNumber: Int = -1
}
