package com.hernanbosqued.movie_db_client.domain

import com.google.gson.annotations.SerializedName

open class ListModel<T : ResultModel> {

    @SerializedName("total_results")
    var totalResults: Int = -1

    @SerializedName("total_pages")
    var totalPages: Int = -1

    @SerializedName("page")
    var page: Int = -1

    open var results: MutableList<T>? = null
}