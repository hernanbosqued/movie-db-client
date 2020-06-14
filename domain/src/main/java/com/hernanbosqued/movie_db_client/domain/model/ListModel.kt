package com.hernanbosqued.movie_db_client.domain.model

import com.google.gson.annotations.SerializedName

class ListModel : BaseListModel<ResultModel>()

class MovieListModel : BaseListModel<MovieResultModel>()
class TVListModel : BaseListModel<TVResultModel>()
class PeopleListModel : BaseListModel<PersonResultModel>()

open class BaseListModel<T:ResultModel> {
    var title: String? = null

    @SerializedName("total_pages")
    var totalPages = -1

    open var results: MutableList<T?> = ArrayList()
}