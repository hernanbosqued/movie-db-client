package com.hernanbosqued.movie_db_client.domain

import com.google.gson.annotations.SerializedName

open class BaseListModel<T : ResultModel> {
    var title: String? = null

    @SerializedName("total_pages")
    var totalPages:Int = -1

    open var results: MutableList<T> = ArrayList()
}