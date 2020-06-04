package com.hernanbosqued.domain.model

import com.google.gson.annotations.SerializedName

class ResultModel{
    @SerializedName("original_title")
    val originalTitle: String = ""
    @SerializedName("vote_average")
    val voteAverage: String = ""
    @SerializedName("backdrop_path")
    val backdropPath: String = ""
    @SerializedName("poster_path")
    val posterPath: String = ""
}