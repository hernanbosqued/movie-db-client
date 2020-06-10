package com.hernanbosqued.domain.model

import com.google.gson.annotations.SerializedName

class VideoResultModel {
    @SerializedName("key")
    var key: String = ""

    @SerializedName("name")
    var name: String = ""

    @SerializedName("site")
    var site: String = ""
}