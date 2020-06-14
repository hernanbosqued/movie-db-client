package com.hernanbosqued.movie_db_client.domain

import java.io.Serializable

class CarouselItemModel private constructor(val title: String? = null, val path: String? = null, val ranking: String? = null, val type: String? = null) : Serializable {

    data class Builder(var title: String? = null, var path: String? = null, var ranking: String? = null, var type: String? = null) {
        private var not = "NOT PROVIDED"

        fun title(title: String?) = apply { this.title = title ?: not }
        fun path(path: String?) = apply { this.path = path ?: not }
        fun ranking(ranking: String?) = apply { this.ranking = ranking ?: not }
        fun type(type: String?) = apply { this.type = type ?: not }

        fun build(): CarouselItemModel = CarouselItemModel(title = title, path = path, ranking = ranking, type = type)
    }
}
