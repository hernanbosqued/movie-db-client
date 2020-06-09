package com.hernanbosqued.domain.model

import com.google.gson.annotations.SerializedName

class ListModel{

    var title: String = ""

    @SerializedName("total_pages")
    var totalPages = 1

    @SerializedName("results")
    var results: MutableList<ResultModel> = ArrayList()
}