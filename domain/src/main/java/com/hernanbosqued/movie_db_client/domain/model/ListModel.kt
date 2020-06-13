package com.hernanbosqued.movie_db_client.domain.model

import com.google.gson.annotations.SerializedName


open class ListModel {
    var title: String? = null

    @SerializedName("total_pages")
    var totalPages = -1

    open var results: MutableList<ResultModel?> = ArrayList()
}