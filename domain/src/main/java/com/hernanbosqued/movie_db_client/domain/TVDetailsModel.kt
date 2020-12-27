package com.hernanbosqued.movie_db_client.domain

import com.google.gson.annotations.SerializedName

class TVDetailsModel {
    @SerializedName(value = "seasons")
    val seasons: List<SeasonModel>? = null
}
