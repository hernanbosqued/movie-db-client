package com.hernanbosqued.domain.model

import com.google.gson.annotations.SerializedName

class VideoModel{
    @SerializedName("id")
    var id: Int = -1

    @SerializedName("results")
    var results: MutableList<VideoResultModel>? = null
}