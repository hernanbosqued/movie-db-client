package com.hernanbosqued.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ResultModel:Serializable{
    @SerializedName(value = "id")
    val id: Int = -1

    @SerializedName(value = "original_title", alternate = ["original_name"])
    val name: String = ""
    @SerializedName("vote_average")
    val voteAverage: String = ""
    @SerializedName("backdrop_path")
    val backdropPath: String = ""
    @SerializedName("poster_path")
    val posterPath: String = ""


}