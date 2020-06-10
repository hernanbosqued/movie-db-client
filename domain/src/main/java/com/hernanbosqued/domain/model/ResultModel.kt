package com.hernanbosqued.domain.model

import com.google.gson.annotations.SerializedName
import com.sun.beans.TypeResolver.resolve
import java.io.Serializable

class ResultModel : Serializable {

    var type: TYPE = TYPE.ERROR

    fun setType(){
        type = TYPE.resolve(this)
    }

    @SerializedName(value = "id")
    val id: Int = -1

    @SerializedName(value = "name")
    val name: String? = null

    @SerializedName(value = "title")
    val title: String? = null

    @SerializedName("vote_average")
    val voteAverage: String = ""

    @SerializedName("poster_path")
    val posterPath: String? = null

    @SerializedName("overview")
    val overview: String = ""

    @SerializedName("video")
    var hasVideo: Boolean = false
}
